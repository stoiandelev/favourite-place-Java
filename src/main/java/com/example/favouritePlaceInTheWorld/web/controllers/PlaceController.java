package com.example.favouritePlaceInTheWorld.web.controllers;

import com.example.favouritePlaceInTheWorld.model.binding.PlaceAddBindingModel;
import com.example.favouritePlaceInTheWorld.model.entity.CommentEntity;
import com.example.favouritePlaceInTheWorld.model.entity.PlaceEntity;
import com.example.favouritePlaceInTheWorld.model.entity.UserEntity;
import com.example.favouritePlaceInTheWorld.model.service.PlaceServiceModel;
import com.example.favouritePlaceInTheWorld.repository.CommentRepository;
import com.example.favouritePlaceInTheWorld.service.CommentService;
import com.example.favouritePlaceInTheWorld.service.PlaceService;
import com.example.favouritePlaceInTheWorld.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Controller
@RequestMapping("/places")
public class PlaceController {

    private final PlaceService placeService;
    private final ModelMapper modelMapper;
    private final CommentService commentService;
    private final UserService userService;


    public PlaceController(PlaceService placeService, ModelMapper modelMapper,
                           CommentService commentService, UserService userService) {
        this.placeService = placeService;
        this.modelMapper = modelMapper;
        this.commentService = commentService;
        this.userService = userService;
    }

    @GetMapping("/add-place")
    public String add() {
        return "add-form";
    }

    @ModelAttribute
    public PlaceAddBindingModel addPlaceBindingModel() {
        return new PlaceAddBindingModel();
    }

    @PostMapping("/add-place")
    public String addConfirm(@Valid PlaceAddBindingModel placeAddBindingModel,
                             BindingResult bindingResult,
                             RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("placeAddBindingModel", placeAddBindingModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.placeAddBindingModel", bindingResult);
            return "redirect:add-place";
        }

        placeService.add(modelMapper.map(placeAddBindingModel, PlaceServiceModel.class));

        return "redirect:/places/all-places";
    }


    @GetMapping("/all-places")
    public String allPlaces(
            Model model,
            @PageableDefault(page = 0, size = 4) Pageable pageable) {

        model.addAttribute("allPlaces", placeService.findAllPlaces(pageable));

        return "cards";
    }

    @GetMapping("/cards/details/")
    public ModelAndView cardDetails(@RequestParam Long id, ModelAndView modelAndView) {

        modelAndView.setViewName("card-details");
        modelAndView.addObject("place", placeService.findById(id));

        return modelAndView;
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable() Long id) {


        Optional<PlaceEntity> allCommentPlace = Optional.of(Optional.of(placeService.findByIdPlaceOwner(id)).get());
        List<CommentEntity> comments = allCommentPlace.get().getComments();

        List<Long> commentsId = new ArrayList<>();
        for (CommentEntity comment : comments) {
            Long commentId = comment.getId();
            commentsId.add(commentId);
        }

        //This is for current User ID (Principal)
        Long currentUserId = null;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = null;
        if (principal instanceof UserDetails) {
            username = ((UserDetails) principal).getUsername();
        } else {
            username = principal.toString();
        }
        Optional<UserEntity> findByUserName = Optional.ofNullable(userService.findByUserName(username));
        UserEntity entity = findByUserName.get();
        currentUserId = entity.getId();

        List<Long> authorsId = new ArrayList<>();
        for (CommentEntity comment : comments) {
            Long authorId = comment.getAuthor().getId();
            authorsId.add(authorId);
        }

        if (authorsId.contains(currentUserId) || entity.getEmail().equals("stoiandelev89@gmail.com")) {
            commentsId.forEach(commentService::deleteById);
        }

        placeService.deleteById(id);

        return "redirect:/";
    }

}
