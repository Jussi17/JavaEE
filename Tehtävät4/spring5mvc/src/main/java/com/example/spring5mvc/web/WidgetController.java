package com.example.spring5mvc.web;

import com.example.spring5mvc.model.Widget;
import com.example.spring5mvc.repository.WidgetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class WidgetController {

    @Autowired
    private WidgetRepository widgetRepository;

    @GetMapping("/widget/new")
    public String newWidget(Model model) {
        model.addAttribute("widget", new Widget());
        return "widgetform";
    }

    @PostMapping("/widget")
    public String createWidget(Widget widget, Model model) {
        widgetRepository.save(widget);
        return "redirect:/widget/" + widget.getId();
    }

    @GetMapping("/widget/{id}")
    public String getWidgetById(@PathVariable Long id, Model model) {
        model.addAttribute("widget", widgetRepository.findById(id).orElse(new Widget()));
        return "widget";
    }

    @GetMapping("/widgets")
    public String getWidgets(Model model) {
        model.addAttribute("widgets", widgetRepository.findAll());
        return "widgets";
    }

    @GetMapping("/widget/edit/{id}")
    public String editWidget(@PathVariable Long id, Model model) {
        model.addAttribute("widget", widgetRepository.findById(id).orElse(new Widget()));
        return "widgetform";
    }

    @PostMapping("/widget/{id}")
    public String updateWidget(Widget widget) {
        widgetRepository.save(widget);
        return "redirect:/widget/" + widget.getId();
    }

    @GetMapping("/widget/delete/{id}")
    public String deleteWidget(@PathVariable Long id) {
        widgetRepository.deleteById(id);
        return "redirect:/widgets";
    }
}