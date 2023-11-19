package com.example.app;

import com.google.cloud.translate.v3.LocationName;
import com.google.cloud.translate.v3.TranslateTextRequest;
import com.google.cloud.translate.v3.TranslateTextResponse;
import com.google.cloud.translate.v3.Translation;
import com.google.cloud.translate.v3.TranslationServiceClient;


import java.io.IOException;


/**
 * Hello world!
 */
public class Translator {


    public static void main(String[] args) {
        try {
            translateText();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void translateText() throws IOException {
        String projectId = "composite-theme-299715";
        String targetLanguage = "hi";
        String text = "Good morning";

        translateText(projectId, targetLanguage, text);
    }

    public static void translateText(String projectId, String targetLanguage, String text)
            throws IOException {

        try (TranslationServiceClient client = TranslationServiceClient.create()) {
            LocationName parent = LocationName.of(projectId, "global");

            TranslateTextRequest request =
                    TranslateTextRequest.newBuilder()
                            .setParent(parent.toString())
                            .setMimeType("text/plain")
                            .setTargetLanguageCode(targetLanguage)
                            .addContents(text)
                            .build();

            TranslateTextResponse response = client.translateText(request);

            // Display the translation for each input text provided
            for (Translation translation : response.getTranslationsList()) {
                System.out.printf("Translated text: %s\n", translation.getTranslatedText());
            }
        }
    }

}
