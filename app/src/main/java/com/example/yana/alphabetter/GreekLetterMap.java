package com.example.yana.alphabetter;

/**
 * Created by yana on 12/6/2017.
 */

public class GreekLetterMap extends GenericLetterMap {
    private String greekLetters [] = {
            "Α, α",
            "Β, β",
            "Γ, γ",
            "Δ, δ",
            "Ε, ε",
            "Ζ, ζ",
            "Η, η",
            "Θ, θ",
            "Ι, ι",
            "Κ, κ",
            "Λ, λ",
            "Μ, μ",
            "Ν, ν",
            "Ξ, ξ",
            "Ο, ο",
            "Π, π",
            "Ρ, ρ",
            "Σ, σ",
            "Τ, τ",
            "Υ, υ",
            "Φ, φ",
            "Χ, χ",
            "Ψ, ψ",
            "Ω, ω",
    };

    private String greekLetterNames [] = {
            "alpha",
            "beta",
            "gamma",
            "delta",
            "epsilon",
            "zeta",
            "eta",
            "theta",
            "iota",
            "kappa",
            "lambda",
            "mu",
            "nu",
            "xi",
            "omicron",
            "pi",
            "rho",
            "sigma",
            "tau",
            "upsilon",
            "phi",
            "chi",
            "psi",
            "omega"
    };

    // constructor
    public GreekLetterMap() {
        super();
        targetLanguageAlphabetName = "Greek";
        targetLanguageName = "Greek";
        nEntries = 23;
        entryMap = new String[nEntries][nDimensions];
        for (int i = 0; i < nEntries; i++) {
            entryMap[i][targetLanguageIndex] = greekLetters[i];
            entryMap[i][knownLanguageIndex] = greekLetterNames[i];
        }
    }

}
