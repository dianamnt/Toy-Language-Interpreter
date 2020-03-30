package model.statements;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import exceptions.FileExceptions;
import model.FileData;
import model.GeneratorInteger;
import model.PrgState;
import model.iFileTable;
import model.utils.iDictionary;

public class OpenRFile implements iStatement{
	private String fileName;
	private String varName;
	
	public OpenRFile(String fn, String vn)
	{
		this.fileName = fn;
		this.varName = vn;
		
	}
	
	@Override
    public PrgState execute(PrgState state) throws FileExceptions {

        iFileTable<Integer, FileData> fileTable = state.getFileTable();
        iDictionary<String, Integer> dict =state.getSymtable();
        BufferedReader reader;

        for(FileData d: fileTable.getAllValues()) {
            if (d.getFileName().equals(fileName))
                throw new FileExceptions("The file is already opened in FileTable");
        }

        try{
            reader = new BufferedReader(new FileReader(fileName));
         } catch (IOException e) {
             throw new FileExceptions("File not found");
         }

         int id = GeneratorInteger.gen_ID();

         fileTable.add(id, new FileData(fileName, reader));
         dict.setValue(varName, id);

         return null;
    }
	
	@Override
	public String toString(){
        return "open("+varName+", "+fileName+')';
	}
}
