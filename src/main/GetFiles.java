package src.main;

import java.io.File;
import java.io.IOException;
import java.util.Vector;

class GetFiles {
    private File curDir;

    private Vector<String> fileList;

    public GetFiles(String dir) {
        this.curDir = new File(dir);
        this.fileList = new Vector<String>();
        this.populateAll(this.curDir.listFiles());
    }

    private void populate() {
        File[] files = curDir.listFiles();

        for(File file : files) {

            if (file.isFile()) {
                this.fileList.add(file.getName());
            }
        }
    }

    private void populateAll(File[] files) {

        if (files == null || files.length == 0) {
            return;
        }
        Vector<String> dirList = new Vector<String>();

        for(File file : files) {

            try{
                if (file.isFile()) {
                    this.fileList.add(file.getCanonicalPath());
                } else if (file.isDirectory()) {
                    dirList.add(file.getCanonicalPath());
                }
            } catch (IOException e) {
                System.out.println("Unable to extract file name.");
            }
        }

        for(String dirName : dirList) {
            populateAll((new File(dirName).listFiles()));
        }
        return;
    }

    public Vector<String> fetch() {
        return this.fileList;
    }

    public Vector<Vector<String>> fetchBatch(int limit) {
        boolean stop = false;
        final int size = this.fileList.size();
        int start = 0, end = Math.min(limit, size);
        Vector<Vector<String>> result = new Vector<Vector<String>>();

        while (end <= size) {
            result.add(new Vector<String>(this.fileList.subList(start, end)));
            start = end;
            end += limit;

            if(stop) {
               break;
            }

            if(end > size) {
                end = size;
                stop=true;
            }
        }
        return result;
    }
}
