package Labs.Lab06;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Collections;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class DogNamesLab {
	private static String promptForFileName() {
		System.out.println("Enter the file name: ");
		@SuppressWarnings("resource")
		Scanner keyIn = new Scanner(System.in);
		return keyIn.next();
	}

	private static Scanner openFile(String fileName) throws FileNotFoundException {
		File file = new File(fileName);
		while (!file.exists()) {
			file = new File(promptForFileName());
		}
		return new Scanner(file);
	}
	
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);

		// Read data file to build data structure
		ArrayList<Dog> doglist = new ArrayList<>();
		
		try {
			// verify file and create file Scanner
			 Scanner fileReader = openFile("Dog_Names.csv");

			//  Discard header line
			 fileReader.nextLine();
			 
			 while(fileReader.hasNextLine()) {
			 	String line = fileReader.nextLine();
			 	int commaIndex = line.indexOf(',');
			 	String name = line.substring(0, commaIndex).trim();
			 	int count = Integer.parseInt(line.substring(commaIndex+1).trim());
			 	doglist.add(new Dog(name, count));
			 }
			 fileReader.close();
		}
		catch(FileNotFoundException noFile){
			System.out.println("There was an error opening or reading from the file.");
			System.exit(0);
		}

		Scanner readInput = new Scanner(System.in);
		String prompt = "\nWhat do you want to do?\n" 
				+ "\t1. Check a dog name\n" + "\t2. See all the dog names\n"
				 + "\t3. Play a game\n" + "\t4. Exit"
				 		+ ".\n"
				+ "Enter the number corresponding to your choice.";
		
		System.out.println(prompt);
		int option = readInput.nextInt();
		
		switch(option) {
		case 1:
			System.out.println("Enter a dog’s name?");
			String name = in.nextLine();
			int nameCount = getCountForDog(doglist, name);
			System.out.println(name + " is registered " + nameCount + " times.");
			break;
		case 2:
			System.out.println(getDogNamesAlphabetically(doglist));
			break;
		case 3:
			playGuessingGame(doglist, in);
			break;
		default: System.out.println("Invalid option.");
		}
		in.close();
	}

	public static int getCountForDog(ArrayList<Dog> dogs, String name) {
		// TODO: 
		// search the list for the Dog named name 
		// display dogs name and the number of registrations for that name
		for(Dog names : dogs){
			if(names.getDogName().equals(name.toUpperCase())){
				return names.getCount();
			}
		}
		return 0;	
	}
	
	public static String getDogNamesAlphabetically(ArrayList<Dog> dogs) {
		Collections.sort(dogs);
		StringBuilder builder = new StringBuilder();
		for(Dog dog : dogs){
			builder.append(dog);
		}
		String text = builder.toString();
		return text;
	}

	public static void playGuessingGame(ArrayList<Dog> dogs, Scanner readIn) {
		// TODO: implement the guessing game.
		  // while not done playing
			// pull two random Dogs from the list
			// display the names and prompt player to pick the more popular name
		    // read player input
			// increment total number of guesses
			// check registration counts to determine if player is correct
				// if correct, respond and increment number of correct answers
				// if wrong, respond
			// ask user if they want to quit
				// if yes, display number of correct out of total number of guesses
				// if no, continue
		Scanner input = new Scanner(System.in);
		Random index = new Random();
		boolean game = true;
		int winRate = 0;
		int gameCount = 0;
		while(game != false){
			System.out.println("Which name is more popular for Anchorage dogs?" +
					"(Type 1 or 2");
			int i = index.nextInt(dogs.size());
			int j = index.nextInt(dogs.size());
			Dog dog1 = dogs.get(i);
			Dog dog2 = dogs.get(j);
			System.out.println("1. "+dog1.getDogName()+"\n2. "+
					dog2.getDogName());
			String number = input.next();
			if(number.equals("1")){
				if(dog1.getCount() > dog2.getCount()){
					System.out.println("Yes, that's right.");
					winRate++;
				}else if(dog1.getCount() < dog2.getCount()){
					System.out.println("Nope, the more popular dog name is "+dog2.getDogName());
				}else if(dog2.getCount() == dog1.getCount()){
					System.out.println("Correct, they are actually equal");
					winRate++;
				}
			}else if(number.equals("2")){
				if(dog2.getCount() > dog1.getCount()){
					System.out.println("Yes, that's right.");
					winRate++;
				}else if(dog2.getCount() < dog1.getCount()){
					System.out.println("Nope, the more popular dog name is "+dog1.getDogName());
				}else if(dog2.getCount() == dog1.getCount()){
					System.out.println("Correct, they are actually equal");
					winRate++;
				}
			}else{
				throw new IllegalArgumentException("Invalid option");
			}
			gameCount++;
			System.out.println("Do you want to play again? (Y/N)");
			String YN = input.next().toUpperCase();
			if(YN.equals("Y")){
				game = true;
			}else if(YN.equals("N")){
				System.out.println("You guessed correctly "+winRate+" out of "+gameCount+" times");
				game = false;
			}else{
				throw new IllegalArgumentException("Invalid option");
			}
		}
	}


}
