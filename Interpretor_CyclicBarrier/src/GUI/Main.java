package GUI;//package View;
import Model.adt.*;
import Model.expression.*;
import Model.statement.*;
import Model.statement.cyclicBarrier.AwaitStatement;
import Model.statement.cyclicBarrier.NewBarrierStatement;
import Model.statement.declaration.AssignmentStatement;
import Model.statement.declaration.CompoundStatement;
import Model.statement.declaration.NopStatement;
import Model.statement.declaration.VariableDeclarationStatement;
import Model.statement.file.CloseReadFileStatement;
import Model.statement.file.OpenReadFileStatement;
import Model.statement.file.ReadFileStatement;
import Model.statement.heap.HeapAllocationStatement;
import Model.statement.heap.HeapWritingStatement;
import Model.statement.operation.*;
import Model.type.*;
import Model.value.BoolValue;
import Model.value.IntValue;
import Model.value.StringValue;
//import Repository.IRepository;
import Controller.Controller;
import Model.ProgramState;
import Repository.IRepository;
import Repository.Repository;
import Test.TestRunner;
import Exception.InterpretorException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application{

    public static void ExecuteTests(){
        if(TestRunner.runTest())
            System.out.println("All tests successful!\n");
    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        ExecuteTests();
        FXMLLoader loader=new FXMLLoader(getClass().getResource("Programs.fxml"));
        Parent root = loader.load();
        primaryStage.setTitle("Interpreter - Program list");

        ProgramsController controller=loader.getController();
        controller.setProgramsList(getExampleList());

        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    public static void main(String[] args){
        launch(args);
    }

    public List<Controller> getExampleList(){
        List<Controller> examples=new ArrayList<>();

        //0.
        IRepository myRepository0 = new Repository("log0.txt");
        Controller myController0 = newController(Example0(), myRepository0);
        myController0.setDescription("print(9/0);");
        examples.add(myController0);
        //2.
        IRepository myRepository2 = new Repository("log2.txt");
        Controller myController2 = newController(Example2(), myRepository2);
        myController2.setDescription("int a;int b; a=2+3*5; b=a+1; Print(b);");
        examples.add(myController2);
        //4.
        IRepository myRepository4 = new Repository("log4.txt");
        Controller myController4 = newController(Example4(), myRepository4);
        myController4.setDescription("bool a=5; print(a);");
        examples.add(myController4);
        //5.
        IRepository myRepository5 = new Repository("log5.txt");
        Controller myController5 = newController(Example5(), myRepository5);
        myController5.setDescription("string varf; varf=\"test.in\"; openRFile(varf); int varc; readFile(varf,varc); Print(varc); readFile(varf,varc); Print(varc); closeRFile(varf);");
        examples.add(myController5);
        //6.
        IRepository myRepository6 = new Repository("log6.txt");
        Controller myController6 = newController(Example6(), myRepository6);
        myController6.setDescription("int v; v=4; (While (v>0) Print(v); v=v-1); Print(v);");
        examples.add(myController6);
        //11.
        IRepository myRepository11 = new Repository("log11.txt");
        Controller myController11=newController(Example11(),myRepository11);
        myController11.setDescription("Ref int v; new(v,20); Ref Ref int a; new(a,v); new(v,30); new(a,v); print(rH(rH(a)));");
        examples.add(myController11);
        //12.
        IRepository myRepository12 = new Repository("log12.txt");
        Controller myController12=newController(Example12(),myRepository12);
        myController12.setDescription("int v; Ref int a; v=10; new(a,22); fork{wH(a,30); v=32; print(v); print(rH(a))}; print(v); print(rH(a));");
        examples.add(myController12);

        //n1.
        IRepository newRepository1=new Repository("newLog1.txt");
        Controller newController1=newController(newExample1(),newRepository1);
        newController1.setDescription("Ref int a; Ref int b; int c; c=(rh(a)<rh(b))?100:200; print(c); c=((rh(b)-2)>rh(a))?100:200; print(c);");
        examples.add(newController1);
        //n2.
        IRepository newRepository2=new Repository("newLog2.txt");
        Controller newController2=newController(newExample2(),newRepository2);
        newController2.setDescription("Ref int a; new(a,20); (for(v=0;v<3;v=v+1) fork(print(v); v=v*rh(a))); print(rh(a));");
        examples.add(newController2);
        //n3.
        IRepository newRepository3=new Repository("newLog3.txt");
        Controller newController3=newController(newExample3(),newRepository3);
        newController3.setDescription("int a; int b; int c; a=1;b=2;c=5; (switch(a*10) (case (b*c) : print(a);print(b)), (case (10) : print(100);print(200)), (default : print(300))); print(300);");
        examples.add(newController3);
        //n4.
        IRepository newRepository4=new Repository("newLog4.txt");
        Controller newController4=newController(newExample4(),newRepository4);
        newController4.setDescription("int v; v=10; (fork(v=v-1;v=v-1;print(v)); sleep(10); print(v*10);");
        examples.add(newController4);
        //n5.
        IRepository newRepository5=new Repository("newLog5.txt");
        Controller newController5=newController(newExample5(),newRepository5);
        newController5.setDescription("int v; v=0; (repeat (fork(print(v);v=v-1);v=v+1) until v==3); print(v*10);");
        examples.add(newController5);
        //n6.
        IRepository newRepository6=new Repository("newLog6.txt");
        Controller newController6=newController(newExample6(),newRepository6);
        newController6.setDescription(newExample6().toString());
        examples.add(newController6);

        return examples;
    }

    /*public static void main(String[] args)
    {
        ExecuteTests();

        Scanner in = new Scanner(System.in);
        //System.out.print("Please give the log file path: ");
        //String path=in.nextLine();


        //1.
        IRepository myRepository1 = new Repository("log1.txt");
        Controller myController1 = newController(Example1(), myRepository1);
        //2.
        IRepository myRepository2 = new Repository("log2.txt");
        Controller myController2 = newController(Example2(), myRepository2);
        //3.
        IRepository myRepository3 = new Repository("log3.txt");
        Controller myController3 = newController(Example3(), myRepository3);
        //4.
        IRepository myRepository4 = new Repository("log4.txt");
        Controller myController4 = newController(Example4(), myRepository4);
        //5.
        IRepository myRepository5 = new Repository("log5.txt");
        Controller myController5 = newController(Example5(), myRepository5);
        //6.
        IRepository myRepository6 = new Repository("log6.txt");
        Controller myController6=newController(Example6(),myRepository6);
        //7.
        IRepository myRepository7 = new Repository("log7.txt");
        Controller myController7=newController(Example7(),myRepository7);
        //8.
        IRepository myRepository8 = new Repository("log8.txt");
        Controller myController8=newController(Example8(),myRepository8);
        //9.
        IRepository myRepository9 = new Repository("log9.txt");
        Controller myController9=newController(Example9(),myRepository9);
        //10.
        IRepository myRepository10 = new Repository("log10.txt");
        Controller myController10=newController(Example10(),myRepository10);
        //11.
        IRepository myRepository11 = new Repository("log11.txt");
        Controller myController11=newController(Example11(),myRepository11);
        //12.
        IRepository myRepository12 = new Repository("log12.txt");
        Controller myController12=newController(Example12(),myRepository12);


        TextMenu menu = new TextMenu();
        menu.addCommand(new ExitCommand("0","exit"));
        menu.addCommand(new RunCommand("1","int v; v=2; Print(v);",myController1));
        menu.addCommand(new RunCommand("2","int a;int b; a=2+3*5; b=a+1; Print(b);",myController2));
        menu.addCommand(new RunCommand("3","bool a; int v; a=true; (If a Then v=2 Else v=3); Print(v);",myController3));
        menu.addCommand(new RunCommand("4","bool a=5; print(a);",myController4));
        menu.addCommand(new RunCommand("5","string varf; varf=\"test.in\"; openRFile(varf); int varc; readFile(varf,varc); Print(varc); readFile(varf,varc); Print(varc); closeRFile(varf);",myController5));
        menu.addCommand(new RunCommand("6","int v; v=4; (While (v>0) Print(v); v=v-1); Print(v);",myController6));
        menu.addCommand(new RunCommand("7","Ref int v; new(v,20); Ref Ref int a; new(a,v); print(v); print(a);",myController7));
        menu.addCommand(new RunCommand("8","Ref int v; new(v,20); Ref Ref int a; new(a,v); print(rH(v)); print(rH(rH(a))+5);",myController8));
        menu.addCommand(new RunCommand("9","Ref int v; new(v,20); print(rH(v)); wH(v,30); print(rH(v)+5);",myController9));
        menu.addCommand(new RunCommand("10","Ref int v; new(v,20); Ref Ref int a; new(a,v); new(v,30); print(rH(rH(a)));",myController10));
        menu.addCommand(new RunCommand("11","Ref int v; new(v,20); Ref Ref int a; new(a,v); new(v,30); new(a,v); print(rH(rH(a)));",myController11));
        menu.addCommand(new RunCommand("12","int v; Ref int a; v=10; new(a,22); fork{wH(a,30); v=32; print(v); print(rH(a))}; print(v); print(rH(a));",myController12));

        menu.show();

    }*/

    public static Controller newController(IStatement statement, IRepository repository){
        Boolean typeChecker;
        try {
            statement.typeCheck(new Dictionary<>());
            typeChecker=true;
        }
        catch(InterpretorException error){
            System.out.println(error.toString());
            typeChecker=false;
        }

        ProgramState programState = new ProgramState(new MyStack<>(), new Dictionary<>(), new Model.adt.List<>(), new Dictionary<>(), new MyHeap<>(), new BarrierTable<>(),statement);
        repository.clearLogFile();
        Controller controller = new Controller(repository);
        controller.setPassedTypeCheck(typeChecker);
        controller.addProgram(programState);

        return controller;
    }

    public static IStatement makeIt(IStatement... statementList){
        //seq(s1, seq(s2, seq(s3, s4))) -> [s1, s2, s3, s4]
        if(statementList.length==0) {
            return new NopStatement();
        }
        return new CompoundStatement(statementList[0],
                makeIt(Arrays.copyOfRange(statementList,1, statementList.length)));
    }

    public static IStatement newExample1(){
        //Ref int a; Ref int b; int c; c=(rh(a)<rh(b))?100:200; print(c); c=((rh(b)-2)>rh(a))?100:200; print(c);
        //The final Out should be {100,200}
        IStatement newExpression1=makeIt(new VariableDeclarationStatement("a",new ReferenceType(new IntType())),
                new VariableDeclarationStatement("b",new ReferenceType(new IntType())),
                new VariableDeclarationStatement("c",new IntType()),
                new HeapAllocationStatement("a",new ValueExpression(new IntValue(0))),
                new HeapAllocationStatement("b",new ValueExpression(new IntValue(0))),
                new HeapWritingStatement("a",new ValueExpression(new IntValue(1))),
                new HeapWritingStatement("b",new ValueExpression(new IntValue(2))),
                new ConditionalAssignmentStatement("c",
                        new RelationalExpression("<",
                                new HeapReadingExpression(new VariableExpression("a")),
                                new HeapReadingExpression(new VariableExpression("b"))),
                        new ValueExpression(new IntValue(100)),
                        new ValueExpression(new IntValue(200))),
                new PrintStatement(new VariableExpression("c")),
                new ConditionalAssignmentStatement("c",
                        new RelationalExpression(">",
                                new ArithmeticExpression('-',
                                        new HeapReadingExpression(new VariableExpression("b")),
                                        new ValueExpression(new IntValue(2))),
                                new HeapReadingExpression(new VariableExpression("a"))),
                        new ValueExpression(new IntValue(100)),
                        new ValueExpression(new IntValue(200))),
                new PrintStatement(new VariableExpression("c")));
        return newExpression1;
    }

    public static IStatement newExample2(){
        //Ref int a; new(a,20); (for(v=0;v<3;v=v+1) fork(print(v); v=v*rh(a))); print(rh(a));
        //The final Out should be {0,1,2,20}
        IStatement newExpression2=makeIt(new VariableDeclarationStatement("a",new ReferenceType(new IntType())),
                new HeapAllocationStatement("a",new ValueExpression(new IntValue(20))),
                new ForStatement("v",
                        new ValueExpression(new IntValue(0)),
                        new ValueExpression(new IntValue(3)),
                        new ArithmeticExpression('+',new VariableExpression("v"),new ValueExpression(new IntValue(1))),
                        new ForkStatement(makeIt(
                                new PrintStatement(new VariableExpression("v")),
                                new AssignmentStatement("v",new ArithmeticExpression('*',new VariableExpression("v"),new HeapReadingExpression(new VariableExpression("a"))))
                        ))),
                new PrintStatement(new HeapReadingExpression(new VariableExpression("a"))));
        return newExpression2;
    }

    public static IStatement newExample3(){
        //int a; int b; int c; a=1;b=2;c=5; (switch(a*10) (case (b*c) : print(a);print(b)), (case (10) : print(100);print(200)), (default : print(300))); print(300);
        //The final Out should be {1,2,300}
        IStatement newExpression3=makeIt(new VariableDeclarationStatement("a",new IntType()),
                new VariableDeclarationStatement("b",new IntType()),
                new VariableDeclarationStatement("c",new IntType()),
                new AssignmentStatement("a",new ValueExpression(new IntValue(1))),
                new AssignmentStatement("b",new ValueExpression(new IntValue(2))),
                new AssignmentStatement("c",new ValueExpression(new IntValue(5))),
                new SwitchStatement(new ArithmeticExpression('*',new VariableExpression("a"),new ValueExpression(new IntValue(10))),
                        new ArithmeticExpression('*',new VariableExpression("b"),new VariableExpression("c")),
                        new CompoundStatement(new PrintStatement(new VariableExpression("a")),new PrintStatement(new VariableExpression("b"))),
                        new ValueExpression(new IntValue(10)),
                        new CompoundStatement(new PrintStatement(new ValueExpression(new IntValue(100))),new PrintStatement(new ValueExpression(new IntValue(200)))),
                        new PrintStatement(new ValueExpression(new IntValue(300)))),
                new PrintStatement(new ValueExpression(new IntValue(300))));
        return newExpression3;
    }

    public static IStatement newExample4(){
        //int v; v=10; (fork(v=v-1;v=v-1;print(v)); sleep(10); print(v*10);
        //The final Out should be {8,100}
        IStatement newExpression4=makeIt(new VariableDeclarationStatement("v",new IntType()),
                new AssignmentStatement("v",new ValueExpression(new IntValue(10))),
                new ForkStatement(makeIt(new AssignmentStatement("v",new ArithmeticExpression('-',new VariableExpression("v"),new ValueExpression(new IntValue(1)))),
                        new AssignmentStatement("v",new ArithmeticExpression('-',new VariableExpression("v"),new ValueExpression(new IntValue(1)))),
                        new PrintStatement(new VariableExpression("v")))),
                new SleepStatement(10),
                new PrintStatement(new ArithmeticExpression('*',new VariableExpression("v"),new ValueExpression(new IntValue(10)))));
        return newExpression4;
    }

    public static IStatement newExample5(){
        //int v; v=0; (repeat (fork(print(v);v=v-1);v=v+1) until v==3); print(v*10);
        //The final Out should be {0,1,2,30}
        IStatement newExpression5=makeIt(new VariableDeclarationStatement("v", new IntType()),
                new AssignmentStatement("v",new ValueExpression(new IntValue(0))),
                new RepeatUntilStatement(new RelationalExpression("==",new VariableExpression("v"),new ValueExpression(new IntValue(3))),
                        new CompoundStatement(new ForkStatement(new CompoundStatement(new PrintStatement(new VariableExpression("v")),
                                new AssignmentStatement("v",new ArithmeticExpression('-',new VariableExpression("v"),new ValueExpression(new IntValue(1)))))),
                        new AssignmentStatement("v",new ArithmeticExpression('+',new VariableExpression("v"),new ValueExpression(new IntValue(1)))))),
                new PrintStatement(new ArithmeticExpression('*',new VariableExpression("v"),new ValueExpression(new IntValue(10)))));
        return newExpression5;
    }

    public static IStatement newExample6(){
        //int v; v=0; (repeat (fork(print(v);v=v-1);v=v+1) until v==3); print(v*10);
        //The final Out should be {0,1,2,30}
        IStatement newExpression6=makeIt(new VariableDeclarationStatement("v1", new ReferenceType(new IntType())),
                new VariableDeclarationStatement("v2",new ReferenceType(new IntType())),
                new VariableDeclarationStatement("v3",new ReferenceType(new IntType())),
                new VariableDeclarationStatement("cnt",new IntType()),
                new HeapAllocationStatement("v1", new ValueExpression(new IntValue(2))),
                new HeapAllocationStatement("v2", new ValueExpression(new IntValue(3))),
                new HeapAllocationStatement("v3", new ValueExpression(new IntValue(4))),
                new NewBarrierStatement("cnt",new HeapReadingExpression(new VariableExpression("v2"))),
                new ForkStatement(makeIt(
                        new AwaitStatement("cnt"),
                        new HeapWritingStatement("v1", new ArithmeticExpression('*',new HeapReadingExpression(new VariableExpression("v1")),new ValueExpression(new IntValue(10)))),
                        new PrintStatement(new HeapReadingExpression(new VariableExpression("v1"))))),
                new ForkStatement(makeIt(
                        new AwaitStatement("cnt"),
                        new HeapWritingStatement("v2", new ArithmeticExpression('*',new HeapReadingExpression(new VariableExpression("v2")),new ValueExpression(new IntValue(10)))),
                        new HeapWritingStatement("v2", new ArithmeticExpression('*',new HeapReadingExpression(new VariableExpression("v2")),new ValueExpression(new IntValue(10)))),
                        new PrintStatement(new HeapReadingExpression(new VariableExpression("v2"))))),
                new AwaitStatement("cnt"),
                new PrintStatement(new HeapReadingExpression(new VariableExpression("v3"))));
        return newExpression6;
    }

    public static IStatement Example0(){
        //Print(5+3);
        IStatement expression0=new PrintStatement(new ArithmeticExpression(new ValueExpression(new IntValue(9)),
                new ValueExpression(new IntValue(0)), '/'));
        return expression0;
    }

    public static IStatement Example1(){
        //int v; v=2; Print(v);
        /*
        IStatement expression1=new CompoundStatement(
                new VariableDeclarationStatement("v",new IntType()),
                new CompoundStatement(
                        new AssignmentStatement("v",new ValueExpression(new IntValue(2))),
                        new PrintStatement(new VariableExpression("v"))));

         */
        IStatement expression1=makeIt(new VariableDeclarationStatement("v",new IntType()),
                new AssignmentStatement("v",new ValueExpression(new IntValue(2))),
                new PrintStatement(new VariableExpression("v")));
        return expression1;
    }

    public static IStatement Example2(){
        //int a;int b; a=2+3*5; b=a+1; Print(b);
        /*
        IStatement expression2=new CompoundStatement(
                new VariableDeclarationStatement("a",new IntType()),
                new CompoundStatement(
                        new VariableDeclarationStatement("b",new IntType()),
                        new CompoundStatement(
                                new AssignmentStatement("a", new ArithmeticExpression(new ValueExpression(new IntValue(2)),
                                        new ArithmeticExpression(new ValueExpression(new IntValue(3)),new ValueExpression(new IntValue(5)),'*'),'+')),
                                new CompoundStatement(
                                        new AssignmentStatement("b",new ArithmeticExpression(new VariableExpression("a"),new ValueExpression(new IntValue(1)),'+')),
                                        new PrintStatement(new VariableExpression("b")));
        */
        IStatement expression2=makeIt(new VariableDeclarationStatement("a",new IntType()),
                new VariableDeclarationStatement("b",new IntType()),
                new AssignmentStatement("a", new ArithmeticExpression(new ValueExpression(new IntValue(2)),
                        new ArithmeticExpression(new ValueExpression(new IntValue(3)),new ValueExpression(new IntValue(5)),'*'),'+')),
                new AssignmentStatement("b",new ArithmeticExpression(new VariableExpression("a"),new ValueExpression(new IntValue(1)),'+')),
                new PrintStatement(new VariableExpression("b")));
        return expression2;
    }

    public static IStatement Example3() {
        //bool a; int v; a=true; (If a Then v=2 Else v=3); Print(v);
        /*
        IStatement expression3=new CompoundStatement(
                new VariableDeclarationStatement("a",new BoolType()),
                new CompoundStatement(
                        new VariableDeclarationStatement("v",new IntType()),
                        new CompoundStatement(
                                new AssignmentStatement("a",new ValueExpression(new BoolValue(true))),
                                new CompoundStatement(
                                        new IfStatement(
                                                new VariableExpression("a"),
                                                new AssignmentStatement("v",new ValueExpression(new IntValue(2))),
                                                new AssignmentStatement("v",new ValueExpression(new IntValue(3)))),
                                        new PrintStatement(new VariableExpression("v"))))));
         */
        IStatement expression3=makeIt(new VariableDeclarationStatement("a",new BoolType()),
                new VariableDeclarationStatement("v",new IntType()),
                new AssignmentStatement("a",new ValueExpression(new BoolValue(true))),
                new IfStatement(
                        new VariableExpression("a"),
                        new AssignmentStatement("v",new ValueExpression(new IntValue(2))),
                        new AssignmentStatement("v",new ValueExpression(new IntValue(3)))),
                new PrintStatement(new VariableExpression("v")));
        return expression3;
    }

    public static IStatement Example4(){
        //bool a=5; print(a);
        IStatement expression4=new CompoundStatement(
                new VariableDeclarationStatement("a",new BoolType()),
                new CompoundStatement(
                        new AssignmentStatement("a",new ValueExpression(new IntValue(5))),
                        new PrintStatement(new VariableExpression("a"))));
        return expression4;
    }

    public static IStatement Example5(){
        //string varf; varf="test.in"; openRFile(varf); int varc; readFile(varf,varc); print(varc); readFile(varf,varc); print(varc); closeRFile(varf);
        IStatement expression5=makeIt(new VariableDeclarationStatement("varf",new StringType()),
                new AssignmentStatement("varf", new ValueExpression(new StringValue("test.in"))),
                new OpenReadFileStatement(new VariableExpression("varf")),
                new VariableDeclarationStatement("varc",new IntType()),
                new ReadFileStatement(new VariableExpression("varf"),"varc"),
                new PrintStatement(new VariableExpression("varc")),
                new ReadFileStatement(new VariableExpression("varf"),"varc"),
                new PrintStatement(new VariableExpression("varc")),
                new CloseReadFileStatement(new VariableExpression("varf")));
        return expression5;
    }

    public static IStatement Example6(){
        //int v; v=4; (while (v>0) print(v);v=v-1);print(v)
        IStatement expression6=makeIt(new VariableDeclarationStatement("v", new IntType()),
                new AssignmentStatement("v", new ValueExpression(new IntValue(4))),
                new WhileStatement(new RelationalExpression(new VariableExpression("v"),new ValueExpression(new IntValue(0)),">"),
                new CompoundStatement(new PrintStatement(new VariableExpression("v")),
                        new AssignmentStatement("v", new ArithmeticExpression(new VariableExpression("v"),new ValueExpression(new IntValue(1)),'-')))),
                new PrintStatement(new VariableExpression("v")));
        return expression6;
    }

    public static IStatement Example7(){
        //Ref int v; new(v,20); Ref Ref int a; new(a,v); print(v); print(a);
        IStatement expression7=makeIt(new VariableDeclarationStatement("v", new ReferenceType(new IntType())),
                new HeapAllocationStatement("v",new ValueExpression(new IntValue(20))),
                new VariableDeclarationStatement("a",new ReferenceType(new ReferenceType(new IntType()))),
                new HeapAllocationStatement("a",new VariableExpression("v")),
                new PrintStatement(new VariableExpression("v")),
                new PrintStatement(new VariableExpression("a")));

        return expression7;
    }

    public static IStatement Example8(){
        //Ref int v; new(v,20); Ref Ref int a; new(a,v); print(rH(v)); print(rH(rH(a))+5);
        IStatement expression8=makeIt(new VariableDeclarationStatement("v", new ReferenceType(new IntType())),
                new HeapAllocationStatement("v",new ValueExpression(new IntValue(20))),
                new VariableDeclarationStatement("a",new ReferenceType(new ReferenceType(new IntType()))),
                new HeapAllocationStatement("a",new VariableExpression("v")),
                new PrintStatement(new HeapReadingExpression(new VariableExpression("v"))),
                new PrintStatement(new ArithmeticExpression('+',new ValueExpression(new IntValue(5)),
                        new HeapReadingExpression(new HeapReadingExpression(new VariableExpression("a"))))));

        return expression8;
    }

    public static IStatement Example9(){
        //Ref int v; new(v,20); print(rH(v)); wH(v,30); print(rH(v)+5);
        IStatement expression9=makeIt(new VariableDeclarationStatement("v", new ReferenceType(new IntType())),
                new HeapAllocationStatement("v",new ValueExpression(new IntValue(20))),
                new PrintStatement(new HeapReadingExpression(new VariableExpression("v"))),
                new HeapWritingStatement("v",new ValueExpression(new IntValue(30))),
                new PrintStatement(new ArithmeticExpression('+',new ValueExpression(new IntValue(5)),
                        new HeapReadingExpression(new VariableExpression("v")))));

        return expression9;
    }

    public static IStatement Example10(){
        //Ref int v; new(v,20); Ref Ref int a; new(a,v); new(v,30); print(rH(rH(a)));
        IStatement expression10=makeIt(new VariableDeclarationStatement("v", new ReferenceType(new IntType())),
                new HeapAllocationStatement("v",new ValueExpression(new IntValue(20))),
                new VariableDeclarationStatement("a",new ReferenceType(new ReferenceType(new IntType()))),
                new HeapAllocationStatement("a",new VariableExpression("v")),
                new HeapAllocationStatement("v", new ValueExpression(new IntValue(30))),
                new PrintStatement(new HeapReadingExpression(new HeapReadingExpression(new VariableExpression("a")))));

        return expression10;
    }

    public static IStatement Example11(){
        //Ref int v; new(v,20); Ref Ref int a; new(a,v); new(v,30); new(a,v); print(rH(rH(a)));
        IStatement expression11=makeIt(new VariableDeclarationStatement("v", new ReferenceType(new IntType())),
                new HeapAllocationStatement("v",new ValueExpression(new IntValue(20))),
                new VariableDeclarationStatement("a",new ReferenceType(new ReferenceType(new IntType()))),
                new HeapAllocationStatement("a",new VariableExpression("v")),
                new HeapAllocationStatement("v", new ValueExpression(new IntValue(30))),
                new HeapAllocationStatement("a",new VariableExpression("v")),
                new PrintStatement(new HeapReadingExpression(new HeapReadingExpression(new VariableExpression("a")))));

        return expression11;
    }

    public static IStatement Example12(){
        //int v; Ref int a; v=10; new(a,22); fork{wH(a,30); v=32; print(v); print(rH(a))}; print(v); print(rH(a));
        IStatement expression12=makeIt(new VariableDeclarationStatement("v",new IntType()),
                new VariableDeclarationStatement("a",new ReferenceType(new IntType())),
                new AssignmentStatement("v",new ValueExpression(new IntValue(10))),
                new HeapAllocationStatement("a",new ValueExpression(new IntValue(22))),
                new ForkStatement(
                        makeIt(new HeapWritingStatement("a",new ValueExpression(new IntValue(30))),
                                new AssignmentStatement("v",new ValueExpression(new IntValue(32))),
                                new PrintStatement(new VariableExpression("v")),
                                new PrintStatement(new HeapReadingExpression(new VariableExpression("a"))))),
                new PrintStatement(new VariableExpression("v")),
                new PrintStatement(new HeapReadingExpression(new VariableExpression("a"))));

        return expression12;
    }

}

