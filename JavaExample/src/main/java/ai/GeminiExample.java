package ai;
import com.google.genai.Client;
import com.google.genai.types.GenerateContentResponse;
public class GeminiExample {
    public static void main(String[] args) throws Exception {
        Client client = Client.builder().apiKey(System.getenv("API_KEY")).build();
        String prompt = "Explain in 2 sentences computer science";
        System.out.println("Prompt: " + prompt);
        GenerateContentResponse response =
                client.models.generateContent(
                        "gemini-3-flash-preview",
                        prompt,
                        null);
        System.out.println("Response: ");
        System.out.println(response.text());
    }

}
