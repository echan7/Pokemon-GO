import java.util.ArrayList;
import java.util.Iterator;

/**
 * A PokemonSpecies entry in the Pokedex. Maintains the number of candies associated
 * with the Pokemon species as well as the Trainer's inventory of Pokemon of this
 * species.
 */
public class PokemonSpecies {

  private int pokedexNumber;
  private String speciesName;
  private int candies;

  /**
   * Maintains the list of Pokemon of this species in the Trainer's inventory.
   */
  private ArrayList<Pokemon> caughtPokemon;

  /**
   * Constructor suitable for a newly encountered Pokemon species during the course of the
   * game and for loading species data from a save file.
   *
   * @param pokedexNumber the Pokedex Number for the species
   * @param speciesName the name of the species
   * @param candies the number of candies the player has obtained by catching 
   * or transferring Pokemon of this species
   */
  public PokemonSpecies(int pokedexNumber, String speciesName, int candies) {
    this.pokedexNumber = pokedexNumber;

    // TODO validate
    this.speciesName = speciesName;

    this.candies = candies;

    // construct caughtPokemon
    caughtPokemon = new ArrayList<Pokemon>();
  }
  
  /**
   * Getter methods
   */
  public Integer getPokedexNumber() {
    return pokedexNumber;
  }
  public String getSpeciesName() {
    return speciesName;
  }
  public int getCandies() {
    return candies;
  }

  /**
   * Add a newly caught Pokemon to the player's inventory and
   * increase the number of candies for this PokemonSpecies
   *
   * @param pokemon the newly caught Pokemon
   */
  public void addNewPokemon(Pokemon pokemon) {
    // TODO
	  caughtPokemon.add(pokemon);
	  this.addNewPokemonCandies();
	  
  }

  /**
   * Helper function to load Pokemon from a save file into the player's inventory for this
   * Pokemon Species
   *
   * @param pokemon the pokemon to add to this species
   */
  public void loadPokemon(Pokemon pokemon) {
    caughtPokemon.add(pokemon);
  }

  /**
   * Find a Pokemon of the given combatPower in the player's inventory for this species.
   *
   * @param cp the combatPower of the Pokemon to find
   * @throws PokedexException [Config.POKEMON_NOT_FOUND] if there is no Pokemon with the 
   * given combatPower in the player's inventory.
   * @return the first Pokemon with the provided combatPower
   */
  public Pokemon findPokemon(int cp) throws PokedexException {
    // TODO
	  Iterator<Pokemon> itr1 = caughtPokemon.iterator();
	  boolean foundPokemon = false;
	  while(itr1.hasNext() && !foundPokemon){
		  Pokemon temp1 = itr1.next();
		  if(cp == temp1.getCombatPower()){
			  foundPokemon = true;
			  return temp1;
		  }
	  }
	  throw new PokedexException(Config.POKEMON_NOT_FOUND);
  }

  /**
   * Transfer a Pokemon with the provided combatPower from the player's inventory
   * to the Professor. This removes the Pokemon from the player's inventory and
   * also increases the number of candies the player has associated with this
   * PokemonSpecies.
   *
   * @param cp the combatPower of the Pokemon to transfer
   * @throws PokedexException if the player does not have a Pokemon with the given
   * combatPower
   * @return the transferred Pokemon
   */
  public Pokemon transferPokemon(int cp) throws PokedexException {
    // TODO
	  
	  Iterator<Pokemon> itr1 = caughtPokemon.iterator();
	  boolean foundPokemon = false;
	  while(itr1.hasNext() && !foundPokemon){
		  Pokemon temp1 = itr1.next();
		  if(cp == temp1.getCombatPower()){
			  foundPokemon = true;
			  itr1.remove();
			  this.addTransferCandies();
			  return temp1;
		  }
	  }
	  throw new PokedexException(Config.POKEMON_NOT_FOUND);
  }
  
  /**
   * Check if the player has any Pokemon of this species
   * @return true if the player has Pokemon of this species in his or her inventory
   * and false otherwise
   */
  public boolean isEmpty() {
    // TODO
	  if (caughtPokemon.isEmpty()){
		  return false;
	  }
	  return true;
  }

  /**
   * Increment candies when a new pokemon is caught
   */
  private void addNewPokemonCandies() {
    this.candies += PokemonGO.NEW_POKEMON_CANDIES;
  }
  
  /**
   * Increment candies when a pokemon is transferred to the professor
   */
  private void addTransferCandies() {
    this.candies += PokemonGO.TRANSFER_POKEMON_CANDIES;
  }
  
  /**
   * Prepare a listing of all the Pokemon of this species that are currently in the
   * player's inventory.
   * 
   * @return a String of the form
   *   <cp1> <cp2> ...
   */
  public String caughtPokemonToString() {
    // TODO
	  Iterator<Pokemon> itr1 = caughtPokemon.iterator();
	  String returnList = "";
	  while(itr1.hasNext()){
		  Pokemon temp1 = itr1.next();
		  returnList +=temp1.getCombatPower()+ " ";
	  }
	  return returnList;
  }
  
  /**
   * Prepare a String representing this entire PokemonSpecies. This is used to
   * save the PokemonSpecies (one part of the Pokedex) to a file to
   * save the player's game.
   *
   * @return a String of the form
   *   <pokedexNumber> <speciesName> <candies> [<cp1>, <cp2>, ...]
   */
  public String toString() {
    // TODO
	  String returnList = "";
	  returnList = returnList + this.pokedexNumber + " " + this.speciesName + " " + this.candies + " " + this.caughtPokemonToString();
	  return returnList;
  }
}
