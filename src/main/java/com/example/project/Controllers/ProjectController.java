package com.example.project.Controllers;

import com.example.project.Forms.*;
import com.example.project.Models.Comment;
import com.example.project.Models.CommentCreator;
import com.example.project.Models.Post;
import com.example.project.Models.User;
import com.example.project.Repository.CommentRepository;
import com.example.project.Repository.PostRepository;
import com.example.project.Repository.UserRepository;
import com.example.project.ReturnObjects.CommentRest;
import com.example.project.ReturnObjects.ErrorRest;
import com.example.project.ReturnObjects.PostRest;
import com.example.project.ReturnObjects.UserCleanRest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

//import java.util.Date;
import java.util.*;

@RestController

public class ProjectController  {
    @Autowired
    UserRepository userRepository;
    @Autowired
    PostRepository postRepository;
    @Autowired
    CommentRepository commentRepository;

@ResponseBody
@PostMapping(value = "login", produces = "application/json")
public ResponseEntity login(@RequestBody LoginForm loginForm ) {
    //System.out.println(loginForm);
        User user = userRepository.findByemail(loginForm.getEmail());
        if(user==null){
            ErrorRest er = new ErrorRest("User does not exist");
            return new ResponseEntity(er, HttpStatusCode.valueOf(400));
        }

        if(user.getPassword().equals(loginForm.getPassword())) {
            return new ResponseEntity("Login Successful", HttpStatusCode.valueOf(200));
        }
        else {
            ErrorRest er = new ErrorRest("Username/Password Incorrect");
            return new ResponseEntity(er, HttpStatusCode.valueOf(400));
        }
}@ResponseBody
    @PostMapping(value = "signup", produces = "application/json")
    public ResponseEntity signup(@RequestBody User user ) {
    if(userRepository.findByemail(user.getEmail())!=null){
        ErrorRest er = new ErrorRest("Forbidden, Account already exists");
        return new ResponseEntity(er, HttpStatusCode.valueOf(400));
    }
        else{
            userRepository.save(user);
        return new ResponseEntity("Account Creation Successful",  HttpStatusCode.valueOf(200));
    }
}   @ResponseBody
    @PostMapping("post")
    public ResponseEntity post(@RequestBody Post post ) {

    if(userRepository.findById(post.getUserID()).isPresent()){
//        User tempuser = userRepository.findById(post.getUserID()).get();
//        List<Post> p  osts =  tempuser.getPost();
//        posts.add(post);

        postRepository.save(post);
      //  System.out.println(post.getUserID());
       // System.out.println(post.getDate());
        return new ResponseEntity("Post created successfully", HttpStatusCode.valueOf(200));
    }
    else{
        ErrorRest er = new ErrorRest("User does not exist");
        return new ResponseEntity(er, HttpStatusCode.valueOf(400));
    }

    }
    @ResponseBody
    @PostMapping(value = "comment",  produces = "application/json")
    public ResponseEntity comment(@RequestBody CommentForm commentform ) {
    int userId = commentform.getUserID();
    int postId = commentform.getPostID();
    Optional<User> user = userRepository.findById(userId);

    if(!user.isPresent()){
        ErrorRest er = new ErrorRest("User does not exist");
        return new ResponseEntity(er, HttpStatusCode.valueOf(400));
    }
    Optional<Post> post = postRepository.findById(postId);
    if(!post.isPresent()){
        ErrorRest er = new ErrorRest("Post does not exist");
        return new ResponseEntity(er ,HttpStatusCode.valueOf(400));
    }
    String name = user.get().getName();
    CommentCreator cc = new CommentCreator(userId, name);
    Comment comment = new Comment(commentform.getCommentBody(), cc, postId);
    commentRepository.save(comment);
  //  System.out.println(comment.get);

    return new ResponseEntity("Comment created successfully", HttpStatusCode.valueOf(200));

    }
    @ResponseBody
    @GetMapping(value = "comment",  produces = "application/json")
    public ResponseEntity getComment(@RequestParam int commentID) {
   Optional<Comment> comment = commentRepository.findById(commentID);
   if(comment.isEmpty()){
       ErrorRest er = new ErrorRest("Comment does not exist");
       return new ResponseEntity(er, HttpStatusCode.valueOf(400));

   }
   else{
       CommentRest cr = new CommentRest(comment.get().getCommentID(), comment.get().getCommentBody(),comment.get().getCommentCreator());
       return new ResponseEntity(cr, HttpStatusCode.valueOf(200));

   }
    }
    @ResponseBody
    @GetMapping(value = "user", produces = "application/json")
    public ResponseEntity getUser(@RequestParam String userID) {
    Optional<User> user = userRepository.findById(Integer.valueOf(userID));
    if(!user.isPresent()){
        ErrorRest er = new ErrorRest("User does not exist");
        return new ResponseEntity(er, HttpStatusCode.valueOf(400));
    }
//
//   List<Post> posts = new ArrayList<>();
//    posts = postRepository.findAllByUserID(Integer.valueOf(userID));
//    ArrayList<PostRest> Pr = new ArrayList<>();
//    for(Post post : posts) {
//        List<Comment> comments = commentRepository.findAllBypostID(post.getPostID());
//        List<CommentRest> Cr = new ArrayList<>();
//
//        for (Comment comment : comments) {
//            CommentRest crs = new CommentRest(comment.getCommentID(), comment.getCommentBody(), comment.getCommentCreator());
//            Cr.add(crs);
//
//        }
//      //  System.out.println(comments.size());
//        post.setComments(comments);
//
//        PostRest pr = new PostRest(post.getPostID(), post.getPostBody(), post.getDate(), Cr);
//        Pr.add(pr);
//
//    }
        // user.get().setPost(posts);
    UserCleanRest urc = new UserCleanRest(user.get().getName(),user.get().getUserID(),user.get().getEmail());


        //  UserRest ur = new UserRest(user.get().getName(), user.get().getUserID(), user.get().getEmail(), Pr);

    return new ResponseEntity(urc, HttpStatusCode.valueOf(200));
    }
    @ResponseBody
    @GetMapping(value = "/", produces = "application/json")
    public ResponseEntity getPosts() {
    List<Post> posts = new ArrayList<>();
    List<PostRest> Prs = new ArrayList<>();
   // List<CommentRest> comments = new ArrayList<>();
    posts = postRepository.findAll();

        Collections.sort(posts, (o1, o2) -> Integer.compare(o2.getPostID(), o1.getPostID()));
    for(Post post : posts) {
        List<Comment> comments1 = commentRepository.findAllBypostID(post.getPostID());
        List<CommentRest> Cr = new ArrayList<>();
        for (Comment comment : comments1) {
            CommentRest crs = new CommentRest(comment.getCommentID(), comment.getCommentBody(), comment.getCommentCreator());
            Cr.add(crs);
        }

        post.setComments(comments1);
        PostRest pr = new PostRest(post.getPostID(), post.getPostBody(), post.getDate(), Cr);
        Prs.add(pr);


    }


    return new ResponseEntity(Prs, HttpStatusCode.valueOf(200));

    }
    @GetMapping(value ="post" , produces = "application/json")
    public ResponseEntity getPost(@RequestParam String postID ) {

     Post post = postRepository.findAllBypostID(Integer.valueOf(postID));
    if(post==null){
        ErrorRest er = new ErrorRest("Post does not exist");
        return new ResponseEntity(er, HttpStatusCode.valueOf(400));
    }


        ArrayList<CommentRest> cr = new ArrayList<>();
        for (Comment comment : commentRepository.findAllBypostID(post.getPostID())) {
            CommentRest crs = new CommentRest(comment.getCommentID(), comment.getCommentBody(), comment.getCommentCreator());
            cr.add(crs);
        }
        PostRest pr = new PostRest(post.getPostID(), post.getPostBody(), post.getDate(), cr);


    return new ResponseEntity(pr, HttpStatusCode.valueOf(200));


    }
    @ResponseBody
    @DeleteMapping(value = "post", produces = "application/json")
    public ResponseEntity deletePost(@RequestBody DeletePostForm deletePostForm) {
   // System.out.println(deletePostForm.getPostId());
    Post post = postRepository.findAllBypostID(Integer.valueOf(deletePostForm.getPostId()));
    if(post==null){
        ErrorRest er = new ErrorRest("Post does not exist");
        return new ResponseEntity(er, HttpStatusCode.valueOf(400));
    }
    else {
        postRepository.delete(post);
        List<Comment> comments = commentRepository.findAllBypostID(post.getPostID());
        for(Comment comment : comments){
            commentRepository.delete(comment);
        }
        //Check weather its needed to delete comments attached to post
        return new ResponseEntity("Post deleted", HttpStatusCode.valueOf(200));
    }
    }
    @ResponseBody
    @PatchMapping(value="post", produces = "application/json")
    public ResponseEntity updatePost(@RequestBody PatchPostForm patchPostForm) {
    Post post = postRepository.findAllBypostID(patchPostForm.getPostID());
    if(post==null){
        ErrorRest er = new ErrorRest("Post does not exist");
        return new ResponseEntity(er, HttpStatusCode.valueOf(400));
    }
    else{
       // post.setPostID(patchPostForm.getPostID());
        post.setPostBody(patchPostForm.getPostBody());
       // postRepository.deleteById(patchPostForm.getPostID());
        postRepository.save(post);
        return new ResponseEntity("Post edited successfully", HttpStatusCode.valueOf(200));
    }

    }
    @ResponseBody
    @DeleteMapping(value = "comment", produces = "application/json")
    public ResponseEntity deleteComment(@RequestBody DeleteCommentForm deleteCommentForm) {
       // System.out.println(deletePostForm.getPostId());
        Comment comment = commentRepository.findAllByCommentID(Integer.valueOf(deleteCommentForm.getCommentId()));
        if(comment==null){
            ErrorRest er = new ErrorRest("Comment does not exist");
            return new ResponseEntity(er, HttpStatusCode.valueOf(400));
        }
        else {
            commentRepository.delete(comment);
            return new ResponseEntity("Comment deleted", HttpStatusCode.valueOf(200));
        }
    }
    @ResponseBody
    @PatchMapping(value="comment", produces = "application/json")
    public ResponseEntity updateComment(@RequestBody PatchCommentForm patchCommentForm) {
        Comment comment = commentRepository.findAllByCommentID(patchCommentForm.getCommentID());
        if(comment==null){
            ErrorRest er = new ErrorRest("Comment does not exist");
            return new ResponseEntity(er, HttpStatusCode.valueOf(400));
        }
        else{
            // post.setPostID(patchPostForm.getPostID());
            comment.setCommentBody(patchCommentForm.getCommentBody());
            // postRepository.deleteById(patchPostForm.getPostID());
            commentRepository.save(comment);
            return new ResponseEntity("Comment edited successfully", HttpStatusCode.valueOf(200));
        }

    }



    @GetMapping(value = "users", produces = "application/json")
    public ResponseEntity getUsers() {
        List<User> users = userRepository.findAll();
        List<UserCleanRest> userCleanRests = new ArrayList<>();
      //  List<UserRest> Urs = new ArrayList<>();
        for(User user : users) {

//            List<Post> posts = new ArrayList<>();
////            posts = postRepository.findAllByUserID(Integer.valueOf(user.getUserID()));
////            ArrayList<PostRest> Pr = new ArrayList<>();
////            for (Post post : posts) {
////                List<Comment> comments = commentRepository.findAllBypostID(post.getPostID());
////                List<CommentRest> Cr = new ArrayList<>();
////
////                for (Comment comment : comments) {
////                    CommentRest crs = new CommentRest(comment.getCommentID(), comment.getCommentBody(), comment.getCommentCreator());
////                    Cr.add(crs);
            UserCleanRest ucr = new UserCleanRest(user.getName(), user.getUserID(), user.getEmail());
            userCleanRests.add(ucr);
        }
//
//                }
//                //  System.out.println(comments.size());
//                post.setComments(comments);
//
//                PostRest pr = new PostRest(post.getPostID(), post.getPostBody(), post.getDate(), Cr);
//                Pr.add(pr);
//
//            }
//            user.setPost(posts);
//
//
//            UserRest ur = new UserRest(user.getName(), user.getUserID(), user.getEmail(), Pr);
//            Urs.add(ur);
//        }

        return new ResponseEntity(userCleanRests, HttpStatusCode.valueOf(200));
    }




//    @GetMapping("postComment")
//    public String count( @RequestParam String id) {
//        Optional<Comment> count = commentRepository.findById(Integer.valueOf(id));
//        return count.get().commentCreator.getName();
//
//    }
}