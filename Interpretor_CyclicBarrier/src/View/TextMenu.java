package View;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

public class TextMenu {

    private final Map<String, Command> commands;

    public TextMenu(){
        this.commands=new HashMap<>();
    }

    public void addCommand(Command command){
        commands.put(command.getKey(),command);
    }

    private void printMenu(){
        System.out.println("Please choose an option:");
        for(Command command : commands.values().stream().sorted(Comparator.comparingInt(value -> Integer.parseInt(value.getKey()))).collect(Collectors.toList())){
            String line=String.format("%4s: %s",command.getKey(),command.getDescription());
            System.out.println(line);
        }
    }

    public void show(){
        Scanner scanner=new Scanner(System.in);
        while(true) {
            printMenu();
            System.out.print(">");
            String key=scanner.nextLine();
            Command command=commands.get(key);
            if(command==null){
                System.out.println("Invalid option!\n");
                continue;
            }
            command.execute();
        }
    }

}
