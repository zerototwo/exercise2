package com.io;

import org.apache.hadoop.io.Writable;
import org.apache.hadoop.io.WritableComparable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.util.Objects;

public class PaiWriable implements WritableComparable<PaiWriable> {

    private int id ;
    private String name;

    public int getId() {
        return id;
    }



    public PaiWriable(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PaiWriable that = (PaiWriable) o;
        return id == that.id &&
                Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }


    @Override
    public String toString() {
        return id+"\t"+name;
    }

    @Override
    public void write(DataOutput dataOutput) throws IOException {

        dataOutput.writeInt(id);
        dataOutput.writeUTF(name);

    }

    @Override
    public void readFields(DataInput dataInput) throws IOException {
        this.id = dataInput.readInt();
        this.name = dataInput.readUTF();
    }

    @Override
    public int compareTo(PaiWriable o) {

        int comp = Integer.valueOf(id).compareTo(o.getId());

        if (comp != 0) {
            return comp;
        }


        return this.getName().compareTo(o.getName());
    }

}
