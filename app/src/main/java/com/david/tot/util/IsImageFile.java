package com.david.tot.util;

import java.io.File;
import java.io.FileFilter;

public class IsImageFile implements FileFilter
{
    File file;
    private final String[] okFileExtensions =  new String[] {"jpg", "png", "gif","jpeg"};

    public void ImageFileFilter(File newfile)
    {
        this.file=newfile;
    }

    public boolean accept(File file)
    {
        for (String extension : okFileExtensions)
        {
            if (file.getName().toLowerCase().endsWith(extension))
            {
                return true;
            }
        }
        return false;
    }

}