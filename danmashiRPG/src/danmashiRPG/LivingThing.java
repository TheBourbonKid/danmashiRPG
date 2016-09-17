package danmashiRPG;
// Klasse, die allen Charakteren und Monstern zugrundeliegt

public class LivingThing
{

	
	// Attribute
	private String Name; 		// Name
	private int Strength;		// St�rke Attribut
	private int Agility;		// Agilit�ts Attribut
	private int HitPoints;		// Lebenspunkte
	private int HitPointsMax;	// Maximal-Lebenspunkte
	private int BaseDamage;		// Standartschaden
	
	
	// Default Construktor
	public LivingThing()
	{
		Name = "Ding";
		Strength = 0;
		Agility = 0;
		HitPoints = 0;
		HitPointsMax = 0;
		BaseDamage = 0;
		
	}
	
	// Constructor
	public LivingThing(String name, int str, int agi, int hp, int bd)
	{
		Name = name;
		Strength = str;
		Agility = agi;
		HitPoints = hp;
		HitPointsMax = hp;
		BaseDamage = bd;
		
	}
	
	// Methoden
	
	
	public int useAttribute(int index)	// Standartmethode f�r Proben -> R�ckgabe = Erfolgsgrade / -1 -> Fehlschlag
	{
		int result = UsefullStuff.throwDice(100, 1);
		int successRate = 0;
		int attribute;
		switch(index)
		{
		case 1:
			attribute = Strength;
			break;
		default:
			attribute = Agility;
			break;
		}
		if (attribute > 100)
		{
			successRate = (attribute-100)/10;
			attribute = 100;
		}
		if (result > attribute || result > 95)
		{
			return -1;
		}
		if (result < 6)
		{
			successRate++;
		}
		successRate += (attribute-result)/10;
		return successRate;	
	}
	
	/* 	verwendet, f�r schaden und heilung / r�ckgabewert true -> ziel lebt noch | false -> ziel ist tot
	 	baseDamage des Angreifers, wenn negativ -> Heilung | bei Heilung successes = 0 annehmen 
	 * 	und gesammte Heilung als negativer bD  
	 * 	wenn das leben ver�ndert wird, wird lebensanzeige benutzt
	 */
	public boolean changeHealth(int bD , int success) 
	{
		HitPoints -= bD + success;
		
		// kein �berheilen
		if(HitPoints > HitPointsMax)
			HitPoints = HitPointsMax;
		
		healthBar();
		
		return (HitPoints > 0);
	}
	
	
	/*	Dodge methode, verwendet wenn einem angriff ausgewichen werden soll
	 * 	success = erfolge des Angreifers, 
	 * R�ckgabewert: true -> ausgewichen -> kein Schaden
	 * false -> schaden
	 */
	public boolean dodge(int success, int bD)
	{
		int result = useAttribute(2);
		// ich wei� nicht ob man den schaden direkt mit ins dodge tut, k�nnte man aber machen
		if (result < success)
			changeHealth(bD, success);
		
		return (result >= success);
	}
	
	
	// Show Methoden
	
	//  Show all stats: ausgabe der Stats des dings in der Console
	public void showStats()
	{
		System.out.println(" Name: " + Name);
		System.out.println(" Lebenskraft: " + Integer.toString(HitPoints) + " / " + Integer.toString(HitPointsMax));
		System.out.println(" Grundschaden: " + Integer.toString(BaseDamage));
		System.out.println(" Str: " + Integer.toString(Strength) + "    Agi: " + Integer.toString(Agility));
		System.out.println();
	}

	
	//  Show health: gibt lebensanzeige in der Console aus: mit namen dazu
	public void healthBar()
	{
		System.out.println(" Name: " + Name);
		System.out.println(" Lebenskraft: " + Integer.toString(HitPoints) + " / " + Integer.toString(HitPointsMax));
		System.out.println();
	}
	
	
	
	
	
	
	// set Methoden mit Kontrolle
	public void setName( String name)
	{
		Name = name;
	}
	
	public void setStrength( int str)
	{
		if(str > 0 && str < 1000)
			Strength = str;
	}
	
	public void setAgility( int agi)
	{
		if(agi > 0 && agi < 1000)
			Agility = agi;
	}
	
	public void setHitPointsMax( int hp)
	{
		if(hp > 0 && hp < 1000)
			HitPointsMax = hp;
	}
	public void setHitPoints( int hp)
	{
		if(hp > 0 && hp < 1000)
			HitPoints = hp;
	}
	
	public void setBaseDamage( int bd)
	{
		if(bd > 0 && bd < 1000)
			BaseDamage = bd;
	}

	
	// get methoden
	public String getName()
	{
		return Name;
	}
	
	public int getStrength()
	{
		return Strength;
	}
	
	public int getAgility()
	{
		return Agility;
	}
	
	public int getHitPointsMax()
	{
		return HitPointsMax;
	}
	
	public int getHitPoints()
	{
		return HitPoints;
	}
	
	public int getBaseDamage()
	{
		return BaseDamage;
	}
	
}
