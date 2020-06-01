package Model;

public abstract class Post {
    private String postId;
    private String title;
    private String description;
    private String creator_id;
    private String status;
    private String replies;
    private String fileName;

    //constructor to create a new post
    public Post (String postId, String title, String description, String status, String creator_id, String fileName)
    {
        this.postId = postId;
        this.title = title;
        this.description = description;
        this.status = status;
        this.creator_id = creator_id;
        this.fileName = fileName;
    }

    public Post(String post_id, String title, String description, String status, String image_name) {
        this.postId = post_id;
        this.title = title;
        this.description = description;
        this.status = status;
        this.fileName = image_name;
    }


    //abstract method to handle reply
    public abstract boolean handleReply (Reply reply);


    //abstract class to get reply details
    public abstract String getReplyDetails ();

    //abstract method to insert post details into database
    public abstract void insertDB();


    //accessor for title
    public String getTitle ()
    {
        return this.title;
    }


    //mutator for title
    public void setTitle (String title)
    {
        this.title = title;
    }


    //accessor for description
    public String getDescription ()
    {
        return this.description;
    }


    //mutator for description
    public void setDescription (String description)
    {
        this.description = description;
    }


    //accessor for creator_id
    public String getCreatorId()
    {
        return this.creator_id;
    }


    //mutator for creator_id
    public void setCreator_id (String creator_id)
    {
        this.creator_id = creator_id;
    }


    //accessor for status
    public String getStatus ()
    {
        return this.status;
    }


    //mutator for status
    public void setStatus (String status)
    {
        this.status = status;
    }


    //accessor for replies
    public String getReplies ()
    {
        return this.replies;
    }


    //mutator for replies
    public void setReplies (String replies)
    {
        this.replies = replies;
    }


    //accessor for id
    public String getPostId()
    {
        return this.postId;
    }

    abstract public void generateId();
//    abstract public void selectDB();

    //mutator for id
    public void setPostId(String postId)
    {

       this.postId =  postId;
    }

    public String getFileName() {
        return fileName;
    }
}
