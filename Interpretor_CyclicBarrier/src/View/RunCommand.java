package View;

import Controller.Controller;
import Exception.InterpretorException;

public class RunCommand extends Command{
    private final Controller controller;

    public RunCommand(String key, String description, Controller controller){
        super(key,description);
        this.controller=controller;
    }

    @Override
    public void execute() {
        try{

            this.controller.allStep();
        }
        catch(InterpretorException error)
        {
            System.out.println(error.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
