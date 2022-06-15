package ru.job4j.design.srp.ocp;

import java.util.HashMap;
import java.util.Map;

public class Archive {
    private MemoryDB store;

    public void saveNotice(Notice notice) {
        store.add(notice);
    }

    public Notice find(Notice notice) {
        return store.findByName(notice.getTitle());
    }


    private static class MemoryDB {
        private final Map<String, Notice> values = new HashMap<>();

        public void add(Notice notice) {
        }

        public Notice findByName(String name) {
            return null;
        }
    }

    private static class Notice {
        private String title;
        private int code;

        public Notice(String title, int code) {
            this.title = title;
            this.code = code;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public int getCode() {
            return code;
        }

        public void setCode(int code) {
            this.code = code;
        }
    }
}
