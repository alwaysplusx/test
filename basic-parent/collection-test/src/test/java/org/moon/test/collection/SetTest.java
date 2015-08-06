package org.moon.test.collection;

import static org.junit.Assert.assertEquals;

import java.util.HashSet;
import java.util.TreeSet;

import org.junit.Test;

public class SetTest {

    @Test
    public void hashSetTest() {
        HashSet<HashEntry> set = new HashSet<HashEntry>();
        set.add(null);
        set.add(new HashEntry(1, "abc"));
        assertEquals(2, set.size());
        set.add(new HashEntry(1, "abc"));
        assertEquals(2, set.size());
    }

    @Test
    public void treeSetTest() {
        TreeSet<TreeEntry> set = new TreeSet<TreeEntry>();
        set.add(new TreeEntry(3));
        set.add(new TreeEntry(2));
        set.add(new TreeEntry(1));
        int i = 1;
        for (TreeEntry e : set) {
            assertEquals(e.id, Integer.valueOf(i++));
        }
    }

    class TreeEntry implements Comparable<TreeEntry> {

        protected Integer id;

        public TreeEntry() {
        }

        public TreeEntry(Integer id) {
            this.id = id;
        }

        @Override
        public int hashCode() {
            System.out.println("TreeEntry hashCode has call");
            final int prime = 31;
            int result = 1;
            result = prime * result + ((id == null) ? 0 : id.hashCode());
            return result;
        }

        @Override
        public boolean equals(Object obj) {
            System.out.println("TreeEntry hashCode has call");
            if (this == obj)
                return true;
            if (obj == null)
                return false;
            if (getClass() != obj.getClass())
                return false;
            TreeEntry other = (TreeEntry) obj;
            if (id == null) {
                if (other.id != null)
                    return false;
            } else if (!id.equals(other.id))
                return false;
            return true;
        }

        @Override
        public int compareTo(TreeEntry o) {
            System.out.println("TreeEntry compareTo has call");
            return this.id.compareTo(o.id);
        }

        @Override
        public String toString() {
            return "TreeEntry [id=" + id + "]";
        }

    }

    class HashEntry {
        int id;
        String name;

        public HashEntry() {
        }

        public HashEntry(int id, String name) {
            this.id = id;
            this.name = name;
        }

        @Override
        public int hashCode() {
            System.out.println("HashEntry hashCode has call");
            final int prime = 31;
            int result = 1;
            result = prime * result + id;
            return result;
        }

        @Override
        public boolean equals(Object obj) {
            System.out.println("HashEntry equals has call");
            if (this == obj)
                return true;
            if (obj == null)
                return false;
            if (getClass() != obj.getClass())
                return false;
            HashEntry other = (HashEntry) obj;
            if (id != other.id)
                return false;
            return true;
        }

    }
}
