package log121.lab2.controller;

import log121.lab2.view.View;

import java.util.*;
import java.util.List;

/**

 Classe gérant les commandes en utilisant le pattern Singleton.

 Elle permet d'exécuter des commandes en boucle de manière asynchrone.
 */
public class CommandManager {
    private static CommandManager instance;
    private List<Command> commands;
    private volatile boolean isExecuting;
    private List<Command> addList;
    private List<String> removeList;

    private CommandManager()
    {
        this.commands = new ArrayList<>();
        this.removeList = new ArrayList<>();
        this.addList = new ArrayList<>();
    }

    /**

     Retourne l'instance unique du CommandManager.
     @return L'instance unique du CommandManager
     */
    public static CommandManager getInstance()
    {
        if(instance == null)
            instance = new CommandManager();
        return  instance;
    }

    /**

     Exécute toutes les commandes en boucle de manière asynchrone.
     */
    public void Execute()
    {
        this.commands.stream()
                .filter(Command::isConditionMet)
                .forEach(Command::execute);
    }

    /**

     Ajoute une liste de commandes associées à une vue.
     @param view La vue associée à la liste de commandes
     @param commands La liste de commandes à ajouter
     */
    public void attachCommand(View view, List<Command> commands) {
        commands.forEach(command -> command.setClassId(view.toString()));
        if(this.isExecuting)
        {
            this.addList.addAll(commands);
        }
        else {
            this.commands.addAll(commands);
        }
    }

    /**

     Supprime les commandes associées à une vue.
     @param view La vue dont on veut supprimer les commandes associées
     */
    public void detachCommand(View view)
    {
        if(this.isExecuting)
        {
            removeList.add(view.toString());
        }
        else {
            this.commands.removeIf(command -> command.checkIfCommandOfClass(view));
        }
    }

    /**

     Exécute la queue de suppression des commandes.
     */
    private void executeRemoveQueue()
    {
        if(!removeList.isEmpty()) {
            this.removeList.forEach(view -> commands.removeIf(command -> command.checkIfCommandOfClass(view)));
            removeList = new ArrayList<>();
        }
    }

    /**

     Exécute la queue d'ajout des commandes.
     */
    private void executeAddQueue()
    {
        if(!addList.isEmpty()) {
            commands.addAll(addList);
            addList = new ArrayList<>();
        }
    }

    /**

     Lance l'exécution en boucle des commandes en utilisant un thread.
     */
    public static void launch()
    {
        new Thread(new Runnable() {
            @Override public void run() {
                while (true)
                {
                    getInstance().executeAddQueue();
                    getInstance().executeRemoveQueue();
                    getInstance().isExecuting = true;
                    getInstance().Execute();
                    getInstance().isExecuting = false;
                }
            }
        }).start();
    }



}
