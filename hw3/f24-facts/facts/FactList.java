package facts;

import java.util.ArrayList;
import java.util.Random;


public class FactList{

	private ArrayList<Fact> factList;
	private Random randomGen;

	public FactList()
	{
		this.factList = new ArrayList<Fact>();
		randomGen = new Random (System.currentTimeMillis());
	}

	public void set(Fact f)
	{
		factList.add(f);
	}

	public int getSize(){
		return factList.size();
	}

	public Fact get(int i){
		return (Fact) factList.get (i);
	}

	public FactList search (String searchString, int mode)
	{
		FactList fl = new FactList();
		for (int i = 0; i < factList.size(); i++)
		{
			Fact fact = factList.get(i);
			if (mode == FactSearchMode.AUTHOR_VAL && 
					fact.getAuthor().toLowerCase().indexOf(searchString.toLowerCase()) != -1){  
				fl.set(fact);
			} else if (mode == FactSearchMode.TEXT_VAL && 
					fact.getText().toLowerCase().indexOf(searchString.toLowerCase()) != -1){  
				fl.set(fact);
			} else if (mode == FactSearchMode.TYPE_VAL && 
					fact.getType().toLowerCase().indexOf(searchString.toLowerCase()) != -1){  
				fl.set(fact);
			} else if ((mode == FactSearchMode.ALL_VAL) &&
					(fact.getAuthor().toLowerCase().indexOf (searchString.toLowerCase()) != -1  ||
					fact.getText().toLowerCase().indexOf (searchString.toLowerCase()) != -1 ||
					fact.getType().toLowerCase().indexOf (searchString.toLowerCase()) != -1)){  
				fl.set(fact);
			}
		}
		return fl;
	}

	public Fact getRandom(){
		return factList.get(randomGen.nextInt(factList.size()));
	}

}
