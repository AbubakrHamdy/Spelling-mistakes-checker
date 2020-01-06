package Repositories;

import java.util.List;

public interface Repository <Type,ID> {
    public void Insert(Type T);
    public Type Read(ID id);
    public List<Type> ReadAll();
    public void Update(Type t);


}
