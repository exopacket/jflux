package com.inteliense.jflux.jarg;

public class Launcher {

    public static void execute(String[] args, Keywords keywords) {
        Command cmd = null;
        HandlesCommands container = null;

        if(args.length == 0) {
            Help.getFromKeywords(keywords).print();
            System.exit(1);
        }

        try {
            cmd = new Command(args, keywords) {
                @Override
                public void exit(String message, int code) {
                    System.err.println(message);
                    System.exit(code);
                }
            };
            container = HandlesCommands.create(cmd, keywords);
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("General error.");
            System.exit(1);
        }

        try {
            if (container != null) container.run();
            else throw new Exception("Command not found.");
            System.exit(0);
        } catch(Exception e) {
            System.err.println("Command not found.");
            e.printStackTrace();
            if (container != null) container.printHelp(); //TODO better handling of help
            System.exit(1);
        }
    }


}
