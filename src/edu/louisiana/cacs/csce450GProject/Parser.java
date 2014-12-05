package edu.louisiana.cacs.csce450GProject;

import java.util.Stack;
import java.io.BufferedReader;
import java.io.FileReader;


public class Parser 
{
	String input;
	public static int j=0;
	public static String actionVal;
	public static int gotoValue;
	public static char LHS;
	public static int RHS;
	public static int flag=0;
	public static boolean exitFlag=true;
	public static int i;
	public static int Flag;
	public static int actionFlag;
	public static String Oper;
	public String sTop;
	public String InputTokens;
	public String copyParseTreeEle="";
	public String sTr;
	public String st1;
	public String StkElements="";
	public Stack<String> stack=new Stack<String>();
	public Stack<String> tempStack=new Stack<String>();
	public Stack<String> parseTree=new Stack<String>();
	
 	public static String parseTreeEle;
	public String stackElement;
	public String tempStackEle;
	public String actionLookVal2;
	char parseOpS='S';
	char parseOpR='R';
	int StepNo=1;
	String file;
	
	String [][] lookUpElements;
	public Parser(String _input)
	  {
		file = _input;
		lookUpElements = new String[12][9];
		lookUpElements[0][0]="S5";
		lookUpElements[0][3]="S4";
		lookUpElements[0][6]="1";
		lookUpElements[0][7]="2";
		lookUpElements[0][8]="3";
		lookUpElements[1][1]="S6";
		lookUpElements[1][5]="accept";
		lookUpElements[2][1]="R2";
		lookUpElements[2][2]="S7";
		lookUpElements[2][4]="R2";
		lookUpElements[2][5]="R2";
		lookUpElements[3][1]="R4";
		lookUpElements[3][2]="R4";
		lookUpElements[3][4]="R4";
		lookUpElements[3][5]="R4";
		lookUpElements[4][0]="S5";
		lookUpElements[4][3]="S4";
		lookUpElements[4][6]="8";
		lookUpElements[4][7]="2";
		lookUpElements[4][8]="3";
		lookUpElements[5][1]="R6";
		lookUpElements[5][2]="R6";
		lookUpElements[5][4]="R6";
		lookUpElements[5][5]="R6";
		lookUpElements[6][0]="S5";
		lookUpElements[6][3]="S4";
		lookUpElements[6][7]="9";
		lookUpElements[6][8]="3";
		lookUpElements[7][0]="S5";
		lookUpElements[7][3]="S4";
		lookUpElements[7][8]="10";
		lookUpElements[8][1]="S6";
		lookUpElements[8][4]="S11";
		lookUpElements[9][1]="R1";
		lookUpElements[9][2]="S7";
		lookUpElements[9][4]="R1";
		lookUpElements[9][5]="R1";
		lookUpElements[10][1]="R3";
		lookUpElements[10][2]="R3";
		lookUpElements[10][4]="R3";
		lookUpElements[10][5]="R3";
		lookUpElements[11][1]="R5";
		lookUpElements[11][2]="R5";
		lookUpElements[11][4]="R5";
		lookUpElements[11][5]="R5";
		
	  }

		
	public void parse()
	   {
		try
		{
			BufferedReader br = new BufferedReader(new FileReader(file));
	      
			
			input= br.readLine();
			
			//while((input)!= null )
			//{
				//String input=br.readLine();
				//String id="id";
				if (!(input.contains("id")))
				{
					System.out.println("Ungrammatical Grammer");
					System.exit(0);
				}
				
				input=input.replaceAll(" ", "");
				
				
			//}
			input=input.replaceAll("id","x");
			br.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}

		 
		 String Input=input;
		 stack.push("0");
		 tempStack.push("0");	 
		 while(Input.length()>0)
		 {
			 
		
			 stackElement=stack.peek(); //Top Value of Stack is taken
			 
		 actionVal=getActionValue(stackElement,Input.charAt(i));  //passes action lookup eg:[0,id]
		
			 String copyActionVal=actionVal.substring(1);
			 
			 if ((actionVal.charAt(0))==parseOpS)
			 {
				 //String tmp = (new Character(Input.charAt(i))).toString() + copyActionVal;
				 flag=1;
				 
				 //////
				 InputTokens=Input;
				 InputTokens=InputTokens.replaceAll("x","id");
				 
				 String outputStack=stack.toString();
				 outputStack=outputStack.replaceAll(",", "");
				 outputStack=outputStack.replaceAll(" ", "");
				 outputStack=outputStack.replaceAll("x", "id");
				 outputStack=outputStack.replace('[', ' ');
				 outputStack=outputStack.replace(']', ' ');
				 
				 
				 // To make the action lookUpVal to id from x
				 actionLookVal2 = ""+Input.charAt(i);
				 
				 				 
				if ((actionLookVal2.toLowerCase()).matches("x"))
				 {
					actionLookVal2="id";
					Flag++;
										 
				 }
				
				if(actionLookVal2=="id")
				{
					parseTree.push(actionLookVal2);
					if(Flag==1)
					{
						parseTreeEle=parseTree.peek();
					}
						
					
					//parseTreeEle;
					if (parseTree.size()>=2)
					{
						//String x;
						String TreeEle=parseTree.peek();

						{
							parseTree.pop();
						    String LastEle=parseTree.peek();
						    parseTree.push(TreeEle);

							StkElements=" "+LastEle;
						}
						
						if(parseTree.size()==3)
						{
							parseTree.pop();
							String MiddEle=parseTree.peek();
							parseTree.pop();
							String thirdEle=parseTree.peek();
							parseTree.push(MiddEle);
							parseTree.push(TreeEle);
							StkElements=" "+MiddEle+thirdEle;
						}
					    parseTreeEle=TreeEle.concat(" "+StkElements);
					   
					}
					
					
					
				}
				
				
				 String formatted = "";
				 formatted = String.format("%-10d %-20s %-20s [%s] %10s %50s %-20s", StepNo,outputStack, InputTokens,stackElement+","+actionLookVal2,actionVal,"push"+" "+actionLookVal2+actionVal.substring(1)," "+" "+parseTreeEle);
				 System.out.println(formatted); 
				 
				 
				 StepNo++;
				 
				 stack.push(new Character(Input.charAt(i)).toString());
				 stack.push(copyActionVal.toString());
				 StringBuilder sb=new StringBuilder(Input);
				 sb.deleteCharAt(i);
				 Input=sb.toString();			 
			 }//done if
			 
			 
			 else if ((actionVal.charAt(0)==parseOpR))
			 {
				 //System.out.println("In R");
				 String ruleNum=copyActionVal;
				 setRuleValues(ruleNum);
				 //System.out.println(LHS);
				 //System.out.println(RHS);
				 String outputStack=stack.toString();
				 outputStack=outputStack.replaceAll(",", "");
				 outputStack=outputStack.replaceAll(" ", "");
				 outputStack=outputStack.replaceAll("x", "id");
				 outputStack=outputStack.replace('[', ' ');
				 outputStack=outputStack.replace(']', ' ');
				 
				 if (RHS==1)
				 {
					 stack.pop();
					 stack.pop();					                      
				 }
								 
				 else
				 {
					 for(int ruleCount=0;ruleCount<6;ruleCount++)
					 {
						 stack.pop();
							 
					 }
					
				 }
				 
								 
				 tempStack=stack;
				 
				 tempStackEle=tempStack.peek();
				 String OutputActionVal=actionVal;
				 gotoValue=Integer.parseInt(getActionValue(tempStackEle,LHS));
				    
				 InputTokens=Input;
				 InputTokens=InputTokens.replaceAll("x","id");
				 				 
				 				 
				 tempStackEle=tempStack.peek();				 
				 String outTempStack=tempStack.toString();
				 outTempStack=outTempStack.replaceAll(",", "");
				 outTempStack=outTempStack.replaceAll(" ", "");
				 outTempStack=outTempStack.replace('[', ' ');
				 outTempStack=outTempStack.replace(']', ' ');
				
				//Printing parse Tree
				 String lhs=Character.toString(LHS);
				 
				 
				 if (!(RHS==3))
				 {
					 parseTreeEle=parseTree.peek();
					 parseTree.pop();

					 String TreeEle="["+lhs;
					 
					 TreeEle=TreeEle.concat(" "+parseTreeEle+"]");
					 parseTree.push(TreeEle);
					 String top=parseTree.peek();
					// String str;
					 if(parseTree.size()!=1)
					 {
						 
						 if(parseTree.size()==2)
						 {
							 parseTree.pop();
							 StkElements=parseTree.peek();
							 parseTree.push(top);
						 }
					  
						 if(parseTree.size()==3)
						 {
							 parseTree.pop();
							 String middleELe=parseTree.peek();
							 parseTree.pop();
							 StkElements=parseTree.peek();
							 parseTree.push(middleELe);
							 parseTree.push(top);
							 StkElements=middleELe.concat(StkElements);
						 }
						 //parseTreeEle=top.concat(copyParseTreeEle);
						  
					 }
					 
					 parseTreeEle=top.concat(" "+StkElements);
					 
//					 
				 }
				 
				
				 else 
				 {
					 if (parseTree.size()>=2)
					 {
						 if (parseTree.size()>2)
						 {
							 String TreeEle="["+lhs;
							 String sNext;
							 sTop=parseTree.elementAt(2);
							 sNext=parseTree.elementAt(1);
							 parseTree.pop();
							 parseTree.pop();
							 
							 if((OutputActionVal.charAt(1))=='3')
							 {
								 Oper="*";
								 st1=TreeEle.concat(sNext).concat(Oper).concat(sTop)+"]";
								 
								 parseTreeEle=TreeEle.concat(sNext).concat(Oper).concat(sTop)+"]".concat(parseTree.elementAt(0));
								 parseTree.push(st1);
							 }
							 
							 else if((OutputActionVal.charAt(1))=='1')
							 {
								 Oper="+";
								 st1=TreeEle.concat(sNext).concat(Oper).concat(sTop)+"]";
								 
								 parseTreeEle=TreeEle.concat(sNext).concat(Oper).concat(sTop)+"]".concat(parseTree.elementAt(0));
								 parseTree.push(st1);
							 }
													 

						 }//end of stack size
						 
						 else    //this is for 2 elements in stack
						 {
							 String TreeEle="["+lhs;
														 
							 if((OutputActionVal.charAt(1))=='3')
							 {
//								 Oper="*";
//								 
//								 parseTreeEle=TreeEle.concat(sNext).concat(Oper).concat(st1)+"]";
//								 parseTree.push(parseTreeEle);
							 }
							 
							 else if((OutputActionVal.charAt(1))=='1')
							 {
								 Oper="+";
								 String stackTop=parseTree.peek();
								 parseTree.pop();
								 String LastEle=parseTree.peek();
								 parseTree.pop();
								 parseTreeEle=TreeEle.concat(LastEle).concat(Oper).concat(stackTop)+"]";
								 parseTree.push(parseTreeEle);
							 }
							 else
							 {
								 
								 String sTop=parseTree.peek();
								 parseTree.pop();
								 String sNext=parseTree.peek();
								 parseTreeEle=TreeEle+"(".concat(sTop)+")"+"]";
								 parseTree.push(parseTreeEle);
								 parseTreeEle=parseTreeEle.concat(sNext);
							 }
							 
							 
						 }
					 
					 
					 
					 
					 
					 }//end of parseTree size if
					 

					 
				 }//end of RHS check if
				 
				 
				 
					 
				 String formatted = "";
				 formatted = String.format("%-10d %-20s %-20s [%s] %10s %6c %-7d %-10s [%s]  %5d %11s %-20s", StepNo,outputStack, InputTokens,stackElement+","+Input.charAt(i),OutputActionVal,LHS,RHS,outTempStack,tempStackEle+","+LHS,gotoValue,"push"+" "+LHS+gotoValue,parseTreeEle);
				 System.out.println(formatted);
				 StepNo++;
	 
			    			    
			    stack.push(new Character(LHS).toString());
			    stack.push(new Integer(gotoValue).toString());
			 
			 }
			 
		 } //end of for 
		 
		 
		 
	   }//end of constructor Parser
	
    public String getActionValue(String row,char token)
    {
    	
        
        getColumn(token);
    	
		
	  		
       actionVal=lookUpElements[Integer.parseInt(row)][j];		
       if (actionVal==null)	
       	{
    	   System.out.println("Ungrammattical Grammer ");
    	   System.exit(0);
    	   //continue;
       	}
    	
       else
       	{
    	  // System.out.println(actionVal);
    	   if (actionVal=="accept")
    		   
    	   {
    		   String outputStack=stack.toString();
    		   outputStack=outputStack.replaceAll(",","");
    		   outputStack=outputStack.replaceAll(" ", "");
    		   outputStack=outputStack.replace('[', ' ');
    		   outputStack=outputStack.replace(']', ' ');
    		   String formatted = "";
			   formatted = String.format("%-10d %-20s %-20s [%s] %10s  %90s", StepNo,outputStack, InputTokens,stackElement+","+InputTokens,"accept",parseTreeEle);
			   System.out.println(formatted);
    		   
    		  printParseTree(); 
    		  System.exit(1);
    	   }
    		   
    	   exitFlag=false;
       	}
    	
    
        	
       return actionVal;
    } //end of getActionValue
	
    public void getColumn(char token)        // generates the column number wrt to corresponding state eg: for id column=0,+ column=1
    {
    	switch(token)
    	{
    	
    	case 'x' : 
    	    j=0;
			break;
    	case '+' : 
    		 j=1;
			break;
    	case '*' : 
    		j=2;
			break;
    	case '(' : 
    		j=3;
			break;
    	case ')' : 
    		j=4;
			break;
    	case '$' : 
    		j=5;
			break;
			
    	case 'E':
    		j=6;
    		break;
    	case 'T':
    		j=7;
    		break;
    	case 'F':
    		j=8;
    		break;
    	}
    }
	
    public void setRuleValues(String ruleNum)
    {
    	if (Integer.parseInt(ruleNum)==1)
    	{
    		LHS='E';
			RHS=3;
    	}
    	else if(Integer.parseInt(ruleNum)==2)
    	{
    		LHS='E';
			RHS=1;
    	}
    	else if(Integer.parseInt(ruleNum)==3)
    	{
    		LHS='T';
			RHS=3;
    	}
    	
    	else if(Integer.parseInt(ruleNum)==4)
    	{
    		LHS='T';
			RHS=1;
    	}
    	
    	else if(Integer.parseInt(ruleNum)==5)
    	{
    		LHS='F';
			RHS=3;
    	}
    	else
    	{
    		LHS='F';
			RHS=1;
    	}
    	
    } //end of setRuleValues
    
    
	public void printParseTree()
	   {
		 parseTreeEle=parseTreeEle.replace('[', ' ');
		 parseTreeEle=parseTreeEle.replaceAll(" ", "");
		 parseTreeEle=parseTreeEle.replace('[', ' ');
		 parseTreeEle=parseTreeEle.replace(']', ' ');
		 parseTreeEle=parseTreeEle.replaceAll(" ", "");
		 parseTreeEle=parseTreeEle.replace('d', ' ');
		 parseTreeEle=parseTreeEle.replaceAll(" ", "");
		 
		 int len=parseTreeEle.length();
		 
		 int count=0;
		 for(int i=0;i<len;i++)
		 {
			 

				
				 for(int j=0;j<count;j++)
				   {
				 	 System.out.print("\t");
					 
				   }
				 if (!(parseTreeEle.charAt(i)=='i'))
				   {
					 if(parseTreeEle.charAt(i)=='*')
					 {
						 System.out.print("\t");
					 }
					 
					 
				     System.out.println(parseTreeEle.charAt(i));
				   }
				 else
				 {
					 System.out.println("id");
					 count=0;
				 }
				 
				 if (parseTreeEle.charAt(i)=='+')
				  {
					 count=0;
				  }
				 if(parseTreeEle.charAt(i)=='*')
				 {				 
					 
					 count=1;
				 }
				 if(parseTreeEle.charAt(i)==')')
				 {
					 count=0;
				 }
				 count++;
			 
			
			 			 
			 
			 
			 //System.out.println(\t);
		 }
		 //System.out.println(parseTreeEle);
		
		 
	   }


}  //end of class
