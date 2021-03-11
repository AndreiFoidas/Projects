package Repository;
import Model.ProgramState;
import Exception.InterpretorException;
import java.util.List;

import java.io.IOException;
import java.util.LinkedList;

public interface IRepository {
    void addProgram(ProgramState newProgram);
    //ProgramState getCurrentProgram() throws InterpretorException;
    void logProgramStateExecute(ProgramState program) throws InterpretorException;
    void clearLogFile() throws InterpretorException;
    List<ProgramState> getProgramList();
    void setProgramList(List<ProgramState> newList);
}
