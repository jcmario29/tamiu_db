import java.util.ArrayList;

public class UserList {
    private ArrayList<User> users;

    public UserList()
    {
        this.users = new ArrayList<>();
    }

    public ArrayList<User> getUsers() {
        return users;
    }

    public int searchUser(int id)
    {
        boolean found = false;
        int i = 0;

        while(i < users.size() && !found)
        {
            if(users.get(i).getId() == id)
                found = true;
            else
                i++;
        }

        if (!found)
            return -1;
        else
            return i;

    }
}
