package madlib;

import java.util.Scanner;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.ArrayList;
import java.util.Random;
import org.json.*;

public class Helpers {
	
	/***
	 * Scanner object for Helpers class
	 */
	private static Scanner sc = new Scanner(System.in);
	
	/***
	 * Gets choice from user, holds until choice is within correct bounds
	 * @param None
	 * @return userChoice that is bounded right
	 */
	public static int getChoice() {
		boolean looper = true;
		int choice = -1;
		
		//Keep user until choice is within bounds
		while (looper) {
			System.out.print("--> ");
			
			try {
				choice = Integer.parseInt(sc.nextLine());
			} catch (NumberFormatException exc) {
				System.out.println("Enter a valid integer!");
				choice = -2;
			}
			
			if ((choice < 1) || (choice > 4)) {
				System.out.println("Enter a valid choice!");
			}
			
			else {
				looper = false;
			}
		}
		
		return(choice);
	}
	
	/***
	 * Calls API and returns string of JSON
	 * @param wordType, that is nouns, adjs, or verbs for respective body needed
	 * @return String of JSON containing all words with specified type
	 */
	public static String callApi(String wordType) {
		//Default string phrase
		String myPhrase = "Not full!";
		
		//Setup client and request
		HttpClient client = HttpClient.newHttpClient();
		HttpRequest request = HttpRequest.newBuilder()
				.uri(URI.create("http://localhost:3000/words/" + wordType))
				.GET()
				.build();
		
		try {
			HttpResponse<String> resp = client.send(request, BodyHandlers.ofString());
			
			myPhrase = resp.body();
		} catch (Exception exc) {
			System.out.println(exc);
		}
		
		return(myPhrase);
	}
	
	/***
	 * Converts JSON array to arrayList of strings with each word
	 * @param words JSONArray with each key value word
	 * @return ArrayList of Strings with each word from the JSON in there
	 */
	public static ArrayList<String> jsonToArray(JSONArray words) {
		//Create string array list
		ArrayList<String> wordList = new ArrayList<String>();
		
		//Fill with each string word
		for (int i = 0; i < words.length(); ++i) {
			JSONObject dummy = words.getJSONObject(i);
			
			wordList.add(dummy.getString("word"));
		}
		
		return(wordList);
	}
	
	/***
	 * Uses callAPI and jsonToArray to replace every word in the String array
	 * @param userArray of unreplaced Madlib String
	 * @return String array with Madlib'd words correctly replaced
	 */
	public static String[] replaceWords(String userArray[]) {
		//Get ArrayList of words for each type
		JSONArray nounJson = new JSONArray(callApi("nouns"));
		ArrayList<String> nouns = jsonToArray(nounJson);
		
		JSONArray adjJson = new JSONArray(callApi("adjs"));
		ArrayList<String> adjs = jsonToArray(adjJson);
		
		JSONArray verbJson = new JSONArray(callApi("verbs"));
		ArrayList<String> verbs = jsonToArray(verbJson);
		
		//Create random generator 
		Random generateIndex = new Random();
		
		//Iterate through each word in array and check if it should be replaced
		for (int i = 0; i < userArray.length; ++i) {			
			switch (userArray[i]) {
				case "<noun>.":
					int selectedNounPeriod = generateIndex.nextInt(nouns.size());
					userArray[i] = nouns.get(selectedNounPeriod);
					userArray[i] = userArray[i] + ".";
					
					break;
				case "<noun>":
					int selectedNoun = generateIndex.nextInt(nouns.size());
					userArray[i] = nouns.get(selectedNoun);
					
					break;
				case "<adj>.":
					int selectedAdjPeriod = generateIndex.nextInt(adjs.size());
					userArray[i] = adjs.get(selectedAdjPeriod);
					userArray[i] = userArray[i] + ".";
					
					break;
				case "<adj>":
					int selectedAdj = generateIndex.nextInt(adjs.size());
					userArray[i] = adjs.get(selectedAdj);
					
					break;
				case "<verb>.":
					int selectedVerbPeriod = generateIndex.nextInt(verbs.size());
					userArray[i] = verbs.get(selectedVerbPeriod);
					userArray[i] = userArray[i] + ".";
					
					break;
				case "<verb>":
					int selectedVerb = generateIndex.nextInt(verbs.size());
					userArray[i] = verbs.get(selectedVerb);
					
					break;
			}
		}
		
		return(userArray);
	}
	
}
