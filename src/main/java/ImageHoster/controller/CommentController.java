package ImageHoster.controller;

import ImageHoster.model.Comment;
import ImageHoster.model.User;
import ImageHoster.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

@Controller
public class CommentController {

    @Autowired
    private ImageService imageService;

    @RequestMapping(value = "/image/{imageId}/{imageTitle}/comments", method = RequestMethod.POST)
    public String addComment(@RequestParam("comment") String commentStr, @PathVariable("imageId") int imageId, @PathVariable("imageTitle") String imageTitle, HttpSession session) {
        Comment comment = new Comment();
        User user = (User) session.getAttribute("loggeduser");
        comment.setText(commentStr);
        comment.setUser(user);
        imageService.addComment(comment, imageId);
        return("redirect:/images/"+imageId+"/"+imageTitle);
    }
}
