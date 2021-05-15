package tacos.web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import tacos.Ingredient;
import tacos.Ingredient.Type;
import tacos.Taco;

import javax.validation.Valid;
import java.util.Arrays;
import java.util.List;

@Slf4j
@Controller
@RequestMapping("/design")
public class DesignTacoController {

    @GetMapping
    public String showDesignForm(Model model) {
        List<Ingredient> ingredients = Arrays.asList(
            new Ingredient("FLTO", "Pszenna", Type.WRAP),
            new Ingredient("COTO", "Kukurydziana", Type.WRAP),
            new Ingredient("GRBP", "Mielona wołowina", Type.PROTEIN),
            new Ingredient("CARN", "Kawałki miesa", Type.PROTEIN),
            new Ingredient("TMTO", "Pomidory krojone w kostke", Type.VEGGIES),
            new Ingredient("LETC", "Sałata", Type.VEGGIES),
            new Ingredient("CHED", "Cheddar", Type.CHEESE),
            new Ingredient("JACK", "Monterey Jack", Type.CHEESE),
            new Ingredient("SLSA", "Pikantny Sos Pomidorowy", Type.SAUCE),
            new Ingredient("SRCR", "Śmietana", Type.SAUCE)
        );


        Type[] types = Ingredient.Type.values();
        for (Type type : types) {
            model.addAttribute(
                    type.toString().toLowerCase(),
                    ingredients
                            .stream()
                            .filter(i -> i.getType().equals(type))
                            .toArray()
            );
        }

        model.addAttribute("design", new Taco());

        return "design";
    }

    @PostMapping
    public String processDesign(@Valid @ModelAttribute("design") Taco design, Errors errors, Model model) {
        if (errors.hasErrors()) {
            return "design";
        }

        // Save the taco design...
        // We'll do this in chapter 3
        log.info("Processing design: " + design);

        return "redirect:/orders/current";
    }
}
