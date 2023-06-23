package com.meta.controller;

import com.meta.model.*;
import com.meta.validation.*;
import org.json.simple.JSONObject;

import javax.ws.rs.*;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import java.util.Collection;
import java.util.Objects;

@Path("/")
public class RestController {
    private static final LinkedinController LINKEDIN_CONTROLLER = LinkedinController.getLinkedinControllerInstance();
    private static final PostController POST_CONTROLLER = PostController.getInstance();
    private static final LikeController LIKE_CONTROLLER = LikeController.getInstance();
    private static final CommentController COMMENT_CONTROLLER = CommentController.getInstance();

    /**
     * <p>
     * Signs up with new {@link LinkedinProfile}
     * </p>
     *
     * @param linkedinProfile to fetch the data from the rest api
     * @return User sign up successfully or not
     */
    @POST
    @Path("/signup")
    @Produces(MediaType.APPLICATION_JSON)
    public JSONObject signUp(final LinkedinProfile linkedinProfile) {
        final JSONObject jsonObject = new JSONObject();
        final Validate validate = new Validate();
        final String errorMessage = validate.validation(linkedinProfile, Create.class);

        if (Objects.isNull(errorMessage)) {
            final Long id = LINKEDIN_CONTROLLER.signUp(linkedinProfile);

            if (Objects.isNull(id)) {
                jsonObject.put("Error", "User already exist");
            } else {
                jsonObject.put("Status", "Signup successfully, Your profile id: " + id);
            }
        } else {
            jsonObject.put("Error", errorMessage);
        }

        return jsonObject;
    }

    /**
     * <p>
     * Signs in with the new {@link LinkedinProfile}
     * </p>
     *
     * @param linkedinProfile to fetch the data from the rest api.
     * @return User sign in successfully or not.
     */
    @POST
    @Path("/signin")
    @Produces(MediaType.APPLICATION_JSON)
    public JSONObject signIn(final LinkedinProfile linkedinProfile) {
        final JSONObject jsonObject = new JSONObject();
        final Validate validate = new Validate();
        final String errorMessage = validate.validation(linkedinProfile, Create.class);

        if (Objects.isNull(errorMessage)) {

            if (LINKEDIN_CONTROLLER.signIn(linkedinProfile)) {
                jsonObject.put("Status", "Signin successfully");
            } else {
                jsonObject.put("Status", "Signin failed");
            }
        } else {
            jsonObject.put("Status", errorMessage);
        }

        return jsonObject;
    }

    /**
     * <p>
     * Gets a {@link LinkedinProfile} by its id.
     * </p>
     *
     * @param id is used to fetch the data from the table.
     * @return if id exists returns the user profile.
     */
    @GET
    @Path("/getprofile")
    @Produces(MediaType.APPLICATION_JSON)
    public JSONObject getProfile(@QueryParam("id") final Long id) {
        final JSONObject jsonObject = new JSONObject();
        final Validate validate = new Validate();
        final String message = validate.validation(id, Get.class);

        if (Objects.isNull(message)) {
            final LinkedinProfile linkedinProfile = LINKEDIN_CONTROLLER.get(id);

            if (Objects.isNull(linkedinProfile)) {
                jsonObject.put("Status", "Please enter valid profile id");
            } else {
                jsonObject.put("UserDetail : ", linkedinProfile);
            }
        } else {
            jsonObject.put("Error", message);
        }

        return jsonObject;
    }

    @Path("/deleteprofile")
    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    public JSONObject deleteProfile(@QueryParam("id") final Long id) {
        final JSONObject jsonObject = new JSONObject();
        final Validate validate = new Validate();
        final String message = validate.validation(id, Delete.class);

        if (Objects.isNull(message)) {

            if (LINKEDIN_CONTROLLER.delete(id)) {
                jsonObject.put("Status", "We’ve closed your account successfully");
            } else {
                jsonObject.put("Status", "Please enter a valid id");
            }
        } else {
            jsonObject.put("Error", message);
        }

        return jsonObject;
    }

    /**
     * <p>
     * Creates the new {@link Post}
     * </p>
     */
    @POST
    @Path("/createpost")
    @Produces(MediaType.APPLICATION_JSON)
    public JSONObject createPost(final Post post) {
        final JSONObject jsonObject = new JSONObject();
        final Validate validate = new Validate();
        final String message = validate.validation(post, Create.class);

        if (Objects.isNull(message)) {

            if (Objects.isNull(LINKEDIN_CONTROLLER.get(post.getLinkedinProfile().getId()))) {
                jsonObject.put("Error", "please enter a valid profile id");

                return jsonObject;
            }
            final Long id = POST_CONTROLLER.create(post);

            if (Objects.isNull(id)) {
                jsonObject.put("Error", "please enter a valid profile id");
            } else {
                jsonObject.put("Status", "Post created successfully,Your post id: " + id);
            }
        } else {
            jsonObject.put("Error", message);
        }

        return jsonObject;
    }

    /**
     * <p>
     * Updates the existing {@link Post}
     * </p>
     */
    @Path("/updatepost")
    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public JSONObject updatePost(final Post post) {
        final JSONObject jsonObject = new JSONObject();
        final Validate validate = new Validate();
        final String message = validate.validation(post, Update.class);

        if (Objects.isNull(message)) {

            if (POST_CONTROLLER.update(post)) {
                jsonObject.put("Status", "Successfully post updated");
            } else {
                jsonObject.put("Status", "Please enter valid details");
            }
        } else {
            jsonObject.put("Status", message);
        }

        return jsonObject;
    }

    /**
     * @param id
     * @return
     */
    @Path("/deletepost")
    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    public JSONObject deletePost(@QueryParam("id") final Long id) {
        final JSONObject jsonObject = new JSONObject();
        final Validate validate = new Validate();
        final String message = validate.validation(id, Delete.class);

        if (Objects.isNull(message)) {

            if (POST_CONTROLLER.delete(id)) {
                jsonObject.put("Status", "We’ve deleted your post successfully");
            } else {
                jsonObject.put("Status", "Please enter a valid id");
            }
        } else {
            jsonObject.put("Error", message);
        }

        return jsonObject;
    }

    /**
     * <p>
     * gets a post by its id.
     * </p>
     */
    @GET
    @Path("/getpost")
    @Produces(MediaType.APPLICATION_JSON)
    public JSONObject getPost(@QueryParam("id") final Long id) {
        final JSONObject jsonObject = new JSONObject();
        final Validate validate = new Validate();
        final String message = validate.validation(id, Get.class);

        if (Objects.isNull(message)) {
            final Post post = POST_CONTROLLER.get(id);

            if (Objects.isNull(post)) {
                jsonObject.put("Error", "Please Enter valid post id");
            } else {
                jsonObject.put("UserDetail : ", post);
            }
        } else {
            jsonObject.put("Error", message);
        }

        return jsonObject;
    }

    /**
     * <p>
     * Creates the new post {@link Like}
     * </p>
     */
    @POST
    @Path("/createpostlike")
    @Produces(MediaType.APPLICATION_JSON)
    public JSONObject createPostLike(final Like like) {
        final JSONObject jsonObject = new JSONObject();
        final Validate validate = new Validate();
        final String message = validate.validation(like, Create.class);

        if (Objects.isNull(message)) {
            like.setLike(true);

            if (LIKE_CONTROLLER.createLike(like)) {
                jsonObject.put("Status", "like added successfully");
            } else {
                jsonObject.put("Error", "Please enter valid details");
            }
        } else {
            jsonObject.put("Error", message);
        }

        return jsonObject;
    }

    @POST
    @Path("/createcommentlike")
    @Produces(MediaType.APPLICATION_JSON)
    public JSONObject createCommentLike(final Like like) {
        final JSONObject jsonObject = new JSONObject();
        final Validate validate = new Validate();
        final String message = validate.validation(like, Create.class);

        if (Objects.isNull(message)) {
            like.setLike(true);

            if (LIKE_CONTROLLER.createLike(like)) {
                jsonObject.put("Status", "like added successfully");
            } else {
                jsonObject.put("Error", "Please enter valid details");
            }
        } else {
            jsonObject.put("Error", message);
        }

        return jsonObject;
    }

    @DELETE
    @Path("/removepostlike")
    @Produces(MediaType.APPLICATION_JSON)
    public JSONObject RemovePostLike(final Like like) {
        final JSONObject jsonObject = new JSONObject();
        final Validate validate = new Validate();
        final String message = validate.validation(like, Delete.class);

        if (Objects.isNull(message)) {
            like.setLike(false);
            like.setReaction(null);

            if (LIKE_CONTROLLER.createLike(like)) {
                jsonObject.put("Status", "like removed successfully");
            } else {
                jsonObject.put("Error", "Please enter valid details");
            }
        } else {
            jsonObject.put("Error", message);
        }

        return jsonObject;
    }

    @DELETE
    @Path("/removecommentlike")
    @Produces(MediaType.APPLICATION_JSON)
    public JSONObject RemoveCommentLike(final Like like) {
        final JSONObject jsonObject = new JSONObject();
        final Validate validate = new Validate();
        final String message = validate.validation(like, Delete.class);

        if (Objects.isNull(message)) {
            like.setLike(false);
            like.setReaction(null);

            if (LIKE_CONTROLLER.createLike(like)) {
                jsonObject.put("Status", "like removed successfully");
            } else {
                jsonObject.put("Error", "Please enter valid details");
            }
        } else {
            jsonObject.put("Error", message);
        }

        return jsonObject;
    }
    @GET
    @Path("/getallpostlikes")
    @Produces(MediaType.APPLICATION_JSON)
    public JSONObject getAllPostLikes(@QueryParam("id") final Long id) {
        final JSONObject jsonObject = new JSONObject();
        final Validate validate = new Validate();
        final String message = validate.validation(id, Delete.class);

        if (Objects.isNull(message)) {
            final Collection<Like> postLikes = LIKE_CONTROLLER.getAll(id);

            if (Objects.isNull(postLikes)) {
                jsonObject.put("Error", "Please enter valid post id");
            } else {
                jsonObject.put("Status", postLikes);
            }
        } else {
            jsonObject.put("Error", message);
        }

        return jsonObject;
    }

    @GET
    @Path("/getallcommentlikes")
    @Produces(MediaType.APPLICATION_JSON)
    public JSONObject getAllCommentLikes(@QueryParam("id") final Long commentId, @QueryParam("id") final Long postId) {
        final JSONObject jsonObject = new JSONObject();
        final Validate validate = new Validate();
        final String message = validate.validation((commentId & postId), Delete.class);

        if (Objects.isNull(message)) {
            final Collection<Like> commentLikes = LIKE_CONTROLLER.getAll(commentId, postId);

            if (Objects.isNull(commentLikes)) {
                jsonObject.put("Error", "Please enter valid details");
            } else {
                jsonObject.put("Status", commentLikes);
            }
        } else {
            jsonObject.put("Error", message);
        }

        return jsonObject;
    }
    
    /**
     * <p>
     * Creates the new {@link Comment}
     * </p>
     */
    @POST
    @Path("/createcomment")
    @Produces(MediaType.APPLICATION_JSON)
    public JSONObject createComment(final Comment comment) {
        final JSONObject jsonObject = new JSONObject();
        final Validate validate = new Validate();
        final String message = validate.validation(comment, Create.class);

        if (Objects.isNull(message)) {

            if (Objects.isNull(LINKEDIN_CONTROLLER.get(comment.getPost().getLinkedinProfile().getId()))) {
                jsonObject.put("Error", "please enter a valid profile id");

                return jsonObject;
            }

            if (Objects.isNull(POST_CONTROLLER.get(comment.getPost().getId()))) {
                jsonObject.put("Error", "please enter a valid post id");

                return jsonObject;
            }
            final Long id = COMMENT_CONTROLLER.create(comment);

            if (Objects.isNull(id)) {
                jsonObject.put("Status", "please enter a valid details");
            } else {
                jsonObject.put("Status", "Comment created successfully,Your comment id: " + id);
            }
        } else {
            jsonObject.put("Status", message);
        }

        return jsonObject;
    }

    /**
     * <p>
     * Updates the existing {@link Comment}
     * </p>
     */
    @Path("/updatecomment")
    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    public JSONObject updateComment(final Comment comment) {
        final JSONObject jsonObject = new JSONObject();
        final Validate validate = new Validate();
        final String message = validate.validation(comment, Update.class);

        if (Objects.isNull(message)) {

            if (COMMENT_CONTROLLER.update(comment)) {
                jsonObject.put("Status", "Successfully comment updated");
            } else {
                jsonObject.put("Error", "Please enter valid details");
            }
        } else {
            jsonObject.put("Error", message);
        }

        return jsonObject;
    }

    /**
     * <p>
     * Deletes a comment by its id.
     * </p>
     */
    @Path("/deletecomment")
    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    public JSONObject deleteComment(@QueryParam("id") final Long id) {
        final JSONObject jsonObject = new JSONObject();
        final Validate validate = new Validate();
        final String message = validate.validation(id, Delete.class);

        if (Objects.isNull(message)) {

            if (COMMENT_CONTROLLER.delete(id)) {
                jsonObject.put("Status", "We’ve deleted your comment successfully");
            } else {
                jsonObject.put("Error", "Please enter a valid id");
            }
        } else {
            jsonObject.put("Error", message);
        }

        return jsonObject;
    }

    /**
     * <p>
     * Gets all comment using their profile id.
     * </p>
     */
    @GET
    @Path("/getallcomment")
    @Produces(MediaType.APPLICATION_JSON)
    public JSONObject getAllComment(@QueryParam("id") final Long id) {
        final JSONObject jsonObject = new JSONObject();
        final Validate validate = new Validate();
        final String message = validate.validation(id, Get.class);

        if (Objects.isNull(message)) {
            final Collection<Comment> comments = COMMENT_CONTROLLER.getAll(id);

            if (Objects.isNull(comments)) {
                jsonObject.put("Error", "Please Enter valid comments id");
            } else {
                jsonObject.put("UserDetail : ", comments);
            }
        } else {
            jsonObject.put("Error", message);
        }

        return jsonObject;
    }

    private void availableUser(final LinkedinProfile linkedinProfile) {
        if (Objects.isNull(linkedinProfile.getEmailAddress())) {
            linkedinProfile.setId();
        }
    }
}
