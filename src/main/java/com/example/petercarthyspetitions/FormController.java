package com.example.petercarthyspetitions;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class FormController {

    private List<Petition> petitionsList = new ArrayList<>();

    {
        petitionsList.add(new Petition("Save the Wales", "Wales is a country that is part of the United Kingdom and that's not their fault. It is bordered by England to the east, the Irish Sea to the north and west, the Celtic Sea to the southwest and the Bristol Channel to the south. As of the 2021 census, it had a population of 3,107,494.[1] It has a total area of 21,218 km2 (8,192 sq mi) and over 1,680 miles (2,700 km) of coastline.[7] It is largely mountainous with its higher peaks in the north and central areas, including Snowdon (Yr Wyddfa), its highest summit.[13] The country lies within the north temperate zone and has a changeable, maritime climate. The capital and largest city is Cardiff."));
        petitionsList.add(new Petition("Cats are Class", "Christ I love them. The cat (Felis catus), commonly referred to as the domestic cat or house cat, is the only domesticated species in the family Felidae. Recent advances in archaeology and genetics have shown that the domestication of the cat occurred in the Near East around 7500 BC. It is commonly kept as a house pet and farm cat, but also ranges freely as a feral cat avoiding human contact. It is valued by humans for companionship and its ability to kill vermin. Because of its retractable claws it is adapted to killing small prey like mice and rats. It has a strong flexible body, quick reflexes, sharp teeth, and its night vision and sense of smell are well developed. It is a social species, but a solitary hunter and a crepuscular predator. Cat communication includes vocalizations like meowing, purring, trilling, hissing, growling, and grunting as well as cat body language. It can hear sounds too faint or too high in frequency for human ears, such as those made by small mammals. It also secretes and perceives pheromones."));
        petitionsList.add(new Petition("Ban Ferrets", "That's it, they suck. The ferret (Mustela furo) is a small, domesticated species belonging to the family Mustelidae. The ferret is most likely a domesticated form of the wild European polecat (Mustela putorius), evidenced by their interfertility. Physically, ferrets resemble other mustelids because of their long, slender bodies. Including their tail, the average length of a ferret is about 50 cm (20 in); they weigh between 0.7 and 2.0 kg (1.5 and 4.4 lb); and their fur can be black, brown, white, or a mixture of those colours. The species is sexually dimorphic, with males being considerably larger than females."));
    }

    @GetMapping("/petition")
    public String showPetitionForm(Model model) {
        model.addAttribute("petition", new Petition());
        return "petition-form";
    }

    @PostMapping("/petition")
    public String submitPetitionForm(@ModelAttribute Petition petition) {
        // Add the petition to your list
        petitionsList.add(petition);

        // Redirect to the form again or any other page
        return "redirect:/petition";
    }

    @GetMapping("/petitions")
    public String showPetitions(@RequestParam(required = false) String search, Model model) {
        List<Petition> petitionsToDisplay;
        petitionsToDisplay = petitionsList;

        model.addAttribute("petitions", petitionsToDisplay);
        return "petition-list";
    }

    @GetMapping("/search")
    public String searchPetitions(@RequestParam String search, Model model) {
        // Perform search logic and get matching petitions
        List<Petition> matchingPetitions = petitionsList.stream()
                .filter(petition ->
                           petition.getName().toLowerCase().contains(search.toLowerCase()) ||
                                   petition.getContent().toLowerCase().contains(search.toLowerCase()))
                .collect(Collectors.toList());

        // Pass the matching petitions to the Thymeleaf template
        model.addAttribute("matchingPetitions", matchingPetitions);
        return "search-results";
    }

    @GetMapping("/petition/{id}")
    public String viewPetition(@PathVariable long id, Model model) {
        // Find the petition by ID
        Petition selectedPetition = petitionsList.stream()
                .filter(p -> p.getId() == id)
                .findFirst()
                .orElse(null);

        if (selectedPetition != null) {
            // Pass the selected petition to the Thymeleaf template
            model.addAttribute("selectedPetition", selectedPetition);
            return "petition-details";
        } else {
            // Redirect to a page indicating that the petition was not found
            return "redirect:/petition-not-found";
        }
    }

    @PostMapping("/petition/sign")
    public String signPetition(@RequestParam String name, @RequestParam String email, @RequestParam long petitionId) {
        // Find the petition by ID
        Petition petition = petitionsList.stream()
                .filter(p -> p.getId() == petitionId)
                .findFirst()
                .orElse(null);

        if (petition != null) {
            // Add the new signature to the petition
            Petition.Signature newSignature = new Petition.Signature(name, email);
            petition.getSignatures().add(newSignature);
        }

        // Redirect back to the petition details page
        return "redirect:/petition/" + petitionId;
    }
}
