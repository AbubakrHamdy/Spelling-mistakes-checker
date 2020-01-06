package Entities;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class JobDescriptionFile implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "idfiles")
    private int idfiles;
    @Column( name = "files" ,columnDefinition="LONGBLOB")
    private byte[] files;
    @Column(name="name",columnDefinition="VARCHAR(255)" )
    private String name ;
    @Column(name="path",columnDefinition="VARCHAR(255)" )
    private String path ;
    @Column(name="opened",columnDefinition="INTEGER" )
    private int opened=1;


    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employee_id")
    private Employee employee;

    public JobDescriptionFile() {

    }
    public JobDescriptionFile(JobDescriptionFile f) {
        this.idfiles = f.idfiles;
        this.files = f.files;
        this.name = f.name;
        this.path=f.path;
        this.opened=f.opened;

    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }


    public int getIdfiles() {
        return idfiles;
    }

    public void setIdfiles(int idfiles) {
        this.idfiles = idfiles;
    }

    public void setFiles(byte[] files) {
        this.files = files;
    }

    public byte[] getFiles() {
        return files;
    }

    public int getOpened() {
        return opened;
    }

    public void setOpened(int opened) {
        this.opened = opened;
    }
}
