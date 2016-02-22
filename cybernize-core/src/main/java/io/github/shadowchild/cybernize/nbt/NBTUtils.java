package io.github.shadowchild.cybernize.nbt;


import io.github.shadowchild.cybernize.util.Resource;
import net.darkhax.opennbt.tags.CompoundTag;
import net.darkhax.opennbt.tags.Tag;
import net.darkhax.opennbt.tags.TagCreateException;
import net.darkhax.opennbt.tags.TagRegistry;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

/**
 * Created by Zach Piddock on 22/02/2016.
 */
public class NBTUtils {

    // METHODS BELOW HERE LOAD AN NBT FILE //

    public static CompoundTag loadFile(Resource resource) {

        return loadFile(resource, true);
    }

    public static CompoundTag loadFile(Resource resource, boolean compressed) {

        try {

            InputStream in = new FileInputStream(resource.toFile());

            if(compressed) {

                in = new GZIPInputStream(in);
            }

            Tag tag = readTag(new DataInputStream(in));

            if(!(tag instanceof CompoundTag)) {

                throw new IOException("Root tag is not a CompoundTag!");
            }

            return (CompoundTag)tag;
        } catch(IOException e) {

            e.printStackTrace();
        }

        return null;
    }

    public static Tag readTag(DataInputStream in) throws IOException {

        int id = in.readUnsignedByte();

        if(id == 0) {

            return null;
        }

        String name = in.readUTF();
        Tag tag;

        try {

            tag = TagRegistry.createInstance(id, name);
        } catch(TagCreateException e) {

            throw new IOException("Failed to create tag.", e);
        }

        tag.read(in);
        return tag;
    }

    // METHODS BELOW HERE WRITE AN NBT FILE //

    public static void writeFile(CompoundTag tag, Resource resource) {

        writeFile(tag, resource, true);
    }

    public static void writeFile(CompoundTag tag, Resource resource, boolean compressed) {

        try {

            File file = resource.toFile();

            if(!file.exists()) {

                if(file.getParentFile() != null && !file.getParentFile().exists()) {

                    file.getParentFile().mkdirs();
                }

                file.createNewFile();
            }

            OutputStream out = new FileOutputStream(file);

            if(compressed) {

                out = new GZIPOutputStream(out);
            }

            writeTag(new DataOutputStream(out), tag);
            out.close();
        } catch(IOException e) {

            e.printStackTrace();
        }
    }


    public static void writeTag(DataOutputStream out, Tag tag) throws IOException {

        out.writeByte(TagRegistry.getIdFor(tag.getClass()));
        out.writeUTF(tag.getName());
        tag.write(out);
    }
}
