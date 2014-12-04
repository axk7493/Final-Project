package edu.louisiana.cacs;

import edu.louisiana.cacs.csce450GProject;

public class Main
{
 public static void main(String[] args)
{
   System.out.println("Hello World from Main");
Parser myParser = new Parser(args[0]);
myParser.parse();
 }
}

























public class Main {

	public static void main(String[] args)
	{
		try
		{
			BufferedReader br = new BufferedReader(new FileReader("sample.txt"));
	      
			String input;
			input= br.readLine();
			while((input)!=null  ){
				//String input=br.readLine();
				//String id="id";
				if (!(input.contains("id")))
				{
					System.out.println("Ungrammatical Grammer");
					System.exit(0);
				}
				input=input.replaceAll(" ", "");
				input=input.replaceAll("id","x");
				Parser myParser=new Parser(input);
				myParser.parse();
				//myParser.printParseTree();
			}
			
			br.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		
		
	}
}
