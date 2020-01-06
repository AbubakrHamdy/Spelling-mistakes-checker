package Entities;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class ProcedureFile implements Serializable {

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

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "process_id")
    private Process process;

    public ProcedureFile() {

    }
    public ProcedureFile(ProcedureFile f) {
        this.idfiles = f.idfiles;
        this.files = f.files;
        this.name = f.name;
        this.path=f.path;
        this.opened=f.opened;

    }



    public Process getProcess() {
        return process;
    }

    public void setProcess(Process process) {
        this.process = process;
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
