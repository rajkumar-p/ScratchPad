#include "delegate.hpp"
#include <iostream>

int main(int argc, char* argv[]) {
    TextData text_data("Some data.",
            std::unique_ptr<SpellChecker>(new SpellChecker()));
    text_data.spell_check();
    text_data.set_spell_checker(
            std::unique_ptr<SpellChecker>(new AdvancedSpellChecker()));
    text_data.spell_check();

    return 0;
}