package com.arimac.projectManegement.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Project {
    public String name;
    public String start_date;
    public String end_date;
    public int status;
    public String description;

    public Project( String name, String start_date, String end_date, int status, String description) {
        this.name = name;
        this.start_date = start_date;
        this.end_date = end_date;
        this.status = status;
        this.description = description;
    }
    public Project() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStart_date() {
        return start_date;
    }

    public void setStart_date(String start_date) {
        this.start_date = start_date;
    }

    public String getEnd_date() {
        return end_date;
    }

    public void setEnd_date(String end_date) {
        this.end_date = end_date;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Project{" +
                "name='" + name + '\'' +
                ", start_date='" + start_date + '\'' +
                ", end_date='" + end_date + '\'' +
                ", status=" + status +
                ", description='" + description + '\'' +
                '}';
    }
}
