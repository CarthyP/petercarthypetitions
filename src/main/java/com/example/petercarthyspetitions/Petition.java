package com.example.petercarthyspetitions;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Petition {

    private String name;
    private String content;
    private long id;
    private List<Signature> signatures = new ArrayList<>();

    public Petition(String name, String content) {
        this.name = name;
        this.content = content;
        this.id = generateRandomId();
        this.signatures = new ArrayList<>();
    }

    public Petition() {

    }

    // Getter + Setter for signatures
    public List<Signature> getSignatures() {
        return signatures;
    }
    public void setSignatures(List<Signature> signatures) {
        this.signatures = signatures;
    }

    public String getName() {
        return name;
    }


    public String getContent() {
        return content;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setContent(String content) {
        this.content = content;
    }


    private long generateRandomId() {
        // Generate a random 5-digit ID
        Random random = new Random();
        return 10000 + random.nextInt(90000);
    }

    public long getId() {
        return id;
    }

    // Inner class for Signature
    public static class Signature {
        private String name;
        private String email;

        public Signature(String name, String email) {
            this.name = name;
            this.email = email;
        }

        public Signature() {

        }

        // Getters for Name and Email

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }
    }

}
