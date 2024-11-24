package com.inteliense.jflux.cloud.azure.polly;

import java.io.InputStream;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.polly.AmazonPolly;
import com.amazonaws.services.polly.AmazonPollyClient;
import com.amazonaws.services.polly.model.*;
import com.inteliense.jflux.cloud.aws.AWSRawCredentials;
import javazoom.jl.player.advanced.AdvancedPlayer;

public class Say {

    private static AmazonPolly polly;
    private static Voice voice;

    private static InputStream synthesize(String text, OutputFormat format) {
        SynthesizeSpeechRequest synthReq = new SynthesizeSpeechRequest().withText(text).withVoiceId(VoiceId.Joanna).withOutputFormat(format).withEngine("neural");
        SynthesizeSpeechResult synthRes = polly.synthesizeSpeech(synthReq);
        return synthRes.getAudioStream();
    }

    public static void text(AWSRawCredentials credentials, String text) throws Exception {
        BasicAWSCredentials creds = new BasicAWSCredentials();
        polly = AmazonPollyClient.builder().withCredentials(new AWSStaticCredentialsProvider(creds)).withRegion(Regions.US_EAST_1).build();

        DescribeVoicesRequest describeVoicesRequest = new DescribeVoicesRequest().withEngine(Engine.Neural);

        try {
            String nextToken = null;
            do {
                DescribeVoicesResult describeVoicesResult = polly.describeVoices(describeVoicesRequest);
                nextToken = describeVoicesResult.getNextToken();
                describeVoicesRequest.setNextToken(nextToken);
                voice = describeVoicesResult.getVoices().get(0);
            } while(nextToken != null);
        } catch(Exception e) {
            System.err.println(" ---- ERROR ------");
            e.printStackTrace();
            System.exit(1);
        }

        InputStream speechStream = synthesize(text, OutputFormat.Mp3);
        AdvancedPlayer player = new AdvancedPlayer(speechStream, javazoom.jl.player.FactoryRegistry.systemRegistry().createAudioDevice());

        player.play();
    }

}
