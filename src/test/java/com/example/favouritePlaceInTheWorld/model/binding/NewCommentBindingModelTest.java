package com.example.favouritePlaceInTheWorld.model.binding;

import com.example.favouritePlaceInTheWorld.model.binding.NewCommentBindingModel;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class NewCommentBindingModelTest {

    @Test
    void setTextContent() {
        NewCommentBindingModel newCommentBindingModel = new NewCommentBindingModel();
        newCommentBindingModel.setTextContent("test");
        Assertions.assertEquals(newCommentBindingModel.getTextContent(), "test");
    }
}
