private static void runPythonProgram() {
        try {
        ProcessBuilder pb = new ProcessBuilder("python", "/chemin/vers/le/programme.py");
        pb.start();
        } catch (IOException e) {
        e.printStackTrace();
        }
        }