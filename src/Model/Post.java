package Model;

public abstract class Post {
    private String postId;
    private String title;
    private String description;
    private String creatorId;
    private String status;
    private String replies;
    private String fileName;


    //constructor to create a new post
    public Post (String postId, String title, String description, String status, String creatorId, String fileName)
    {
        this.postId = postId;
        this.title = title;
        this.description = description;
        this.status = status;
        this.creatorId = creatorId;
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
        return this.creatorId;
    }


    //mutator for creator_id
    public void setCreatorId(String creatorId)
    {
        this.creatorId = creatorId;
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


    //abstract method to record the replies to the post
    public abstract void insertReplies(String user) throws duplicateReplyException;


    //method for generating the post id
    abstract public void generateId();

    //mutator for id
    public void setPostId(String postId)
    {

       this.postId =  postId;
    }

    public String getFileName() {
        return fileName;
    }
}
