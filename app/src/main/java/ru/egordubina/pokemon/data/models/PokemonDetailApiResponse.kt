package ru.egordubina.pokemon.data.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * @see <a href="https://pokeapi.co/docs/v2">Full API Doc</a>
 * @see <a href="https://pokeapi.co/docs/v2#pokemon">Current API Doc</a>
 * */
@Serializable
data class PokemonDetailApiResponse(
    @SerialName("abilities") val abilities: List<Ability>, // Способности
    @SerialName("base_experience") val baseExperience: Int, // Опыт
//    @SerialName("cries") val cries: Cries, // Звук, скип
    @SerialName("forms") val forms: List<PokemonForm>, // Форма покемона, например бульбазавр
//    @SerialName("game_indices") val gameIndices: List<GameIndice>, // Список индексов по поколениям (?)
    @SerialName("height") val height: Int, // Высота
    @SerialName("held_items") val heldItems: List<HeldItem>, // Список предметов, которые могут быть у этого покемона при (первой?) встрече
    @SerialName("id") val id: Int, // id
//    @SerialName("is_default") val isDefault: Boolean, // ?
    @SerialName("location_area_encounters") val locationAreaEncounters: String, // Ссылка на список локаций, где этот покемон есть
    @SerialName("moves") val moves: List<Move>, // Список приёмов и методов изучения (?)
    @SerialName("name") val name: String, // Имя
    @SerialName("order") val order: Int, // Для сортировки
//    @SerialName("past_abilities") val pastAbilities: List<String>, // Неизвестно
//    @SerialName("past_types") val pastTypes: List<String>, // Список деталей
    @SerialName("species") val species: Species, // Вид покемона, например бульбазавр (???)
    @SerialName("sprites") val sprites: Sprites, // Спрайты покемона
    @SerialName("stats") val stats: List<Stat>, // Базовые статистики покемона
    @SerialName("types") val types: List<Type>, // Список типов, к котором относится покемон
    @SerialName("weight") val weight: Int, // Ширина
)

@Serializable
data class Ability(
    @SerialName("ability") val ability: AbilityShort, // Name, url способности
    @SerialName("is_hidden") val isHidden: Boolean,
    @SerialName("slot") val slot: Int,
)

@Serializable
data class Cries(
    @SerialName("latest") val latest: String,
    @SerialName("legacy") val legacy: String,
)

@Serializable
data class PokemonForm(
    @SerialName("name") val name: String,
    @SerialName("url") val url: String,
)

@Serializable
data class GameIndice(
    @SerialName("game_index") val gameIndex: Int,
    @SerialName("version") val version: Version,
)

@Serializable
data class HeldItem(
    @SerialName("item") val item: Item,
    @SerialName("version_details") val versionDetails: List<VersionDetail>,
)

@Serializable
data class Move(
    @SerialName("move") val move: MoveX,
    @SerialName("version_group_details") val versionGroupDetails: List<VersionGroupDetail>,
)

@Serializable
data class Species(
    @SerialName("name") val name: String,
    @SerialName("url") val url: String,
)

@Serializable
data class Sprites(
    @SerialName("back_default") val backDefault: String?,
    @SerialName("back_female") val backFemale: String?,
    @SerialName("back_shiny") val backShiny: String?,
    @SerialName("back_shiny_female") val backShinyFemale: String?,
    @SerialName("front_default") val frontDefault: String?,
    @SerialName("front_female") val frontFemale: String?,
    @SerialName("front_shiny") val frontShiny: String?,
    @SerialName("front_shiny_female") val frontShinyFemale: String?,
//    @SerialName("other") val other: Other,
//    @SerialName("versions") val versions: Versions,
)

@Serializable
data class Stat(
    val base_stat: Int,
    val effort: Int,
//    val stat: StatX,
)

@Serializable
data class Type(
    val slot: Int,
//    val type: TypeX,
)

@Serializable
data class AbilityShort(
    val name: String,
    val url: String,
)

@Serializable
data class Version(
    val name: String,
    val url: String,
)

@Serializable
data class Item(
    val name: String,
    val url: String,
)

@Serializable
data class VersionDetail(
    val rarity: Int,
    val version: Version,
)

@Serializable
data class MoveX(
    val name: String,
    val url: String,
)

@Serializable
data class VersionGroupDetail(
    val level_learned_at: Int,
    val move_learn_method: MoveLearnMethod,
    val version_group: VersionGroup,
)

@Serializable
data class MoveLearnMethod(
    val name: String,
    val url: String,
)

@Serializable
data class VersionGroup(
    val name: String,
    val url: String,
)

@Serializable
data class Other(
    val dream_world: DreamWorld,
    val home: Home,
    @SerialName("official-artwork") val officialArtwork: OfficialArtwork,
    val showdown: Showdown,
)

//@Serializable
//data class Versions(
//    val generationI: GenerationI,
//    val generationIi: GenerationIi,
//    val generationIii: GenerationIii,
//    val generationIv: GenerationIv,
//    val generationV: GenerationV,
//    val generationVi: GenerationVi,
//    val generationVii: GenerationVii,
//    val generationViii: GenerationViii,
//)

@Serializable
data class DreamWorld(
    val front_default: String,
    val front_female: String,
)

@Serializable
data class Home(
    val front_default: String,
    val front_female: String,
    val front_shiny: String,
    val front_shiny_female: String,
)

@Serializable
data class OfficialArtwork(
    val front_default: String,
    val front_shiny: String,
)

@Serializable
data class Showdown(
    val back_default: String,
    val back_female: String,
    val back_shiny: String,
    val back_shiny_female: String,
    val front_default: String,
    val front_female: String,
    val front_shiny: String,
    val front_shiny_female: String,
)

//@Serializable
//data class GenerationI(
//    val red-blue: RedBlue,
//    val yellow: Yellow,
//)
//
//@Serializable
//data class GenerationIi(
//    val crystal: Crystal,
//    val gold: Gold,
//    val silver: Silver,
//)
//
//@Serializable
//data class GenerationIii(
//    val emerald: Emerald,
//    val firered-leafgreen: FireredLeafgreen,
//    val ruby-sapphire: RubySapphire,
//)
//
//@Serializable
//data class GenerationIv(
//    val diamond-pearl: DiamondPearl,
//    val heartgold-soulsilver: HeartgoldSoulsilver,
//    val platinum: Platinum,
//)
//
//@Serializable
//data class GenerationV(
//    val black-white: BlackWhite,
//)
//
//@Serializable
//data class GenerationVi(
//    val omegaruby-alphasapphire: OmegarubyAlphasapphire,
//    val x-y: XY,
//)
//
//@Serializable
//data class GenerationVii(
//    val icons: Icons,
//    val ultra-sun-ultra-moon: UltraSunUltraMoon,
//)
//
//@Serializable
//data class GenerationViii(
//    val icons: IconsX,
//)
//
//@Serializable
//data class RedBlue(
//    val back_default: String,
//    val back_gray: String,
//    val back_transparent: String,
//    val front_default: String,
//    val front_gray: String,
//    val front_transparent: String,
//)
//
//@Serializable
//data class Yellow(
//    val back_default: String,
//    val back_gray: String,
//    val back_transparent: String,
//    val front_default: String,
//    val front_gray: String,
//    val front_transparent: String,
//)
//
//@Serializable
//data class Crystal(
//    val back_default: String,
//    val back_shiny: String,
//    val back_shiny_transparent: String,
//    val back_transparent: String,
//    val front_default: String,
//    val front_shiny: String,
//    val front_shiny_transparent: String,
//    val front_transparent: String,
//)
//
//@Serializable
//data class Gold(
//    val back_default: String,
//    val back_shiny: String,
//    val front_default: String,
//    val front_shiny: String,
//    val front_transparent: String,
//)
//
//@Serializable
//data class Silver(
//    val back_default: String,
//    val back_shiny: String,
//    val front_default: String,
//    val front_shiny: String,
//    val front_transparent: String,
//)
//
//@Serializable
//data class Emerald(
//    val front_default: String,
//    val front_shiny: String,
//)
//
//@Serializable
//data class FireredLeafgreen(
//    val back_default: String,
//    val back_shiny: String,
//    val front_default: String,
//    val front_shiny: String,
//)
//
//@Serializable
//data class RubySapphire(
//    val back_default: String,
//    val back_shiny: String,
//    val front_default: String,
//    val front_shiny: String,
//)
//
//@Serializable
//data class DiamondPearl(
//    val back_default: String,
//    val back_female: String,
//    val back_shiny: String,
//    val back_shiny_female: String,
//    val front_default: String,
//    val front_female: String,
//    val front_shiny: String,
//    val front_shiny_female: String,
//)
//
//@Serializable
//data class HeartgoldSoulsilver(
//    val back_default: String,
//    val back_female: String,
//    val back_shiny: String,
//    val back_shiny_female: String,
//    val front_default: String,
//    val front_female: String,
//    val front_shiny: String,
//    val front_shiny_female: String,
//)
//
//@Serializable
//data class Platinum(
//    val back_default: String,
//    val back_female: String,
//    val back_shiny: String,
//    val back_shiny_female: String,
//    val front_default: String,
//    val front_female: String,
//    val front_shiny: String,
//    val front_shiny_female: String,
//)
//
//@Serializable
//data class BlackWhite(
//    val animated: Animated,
//    val back_default: String,
//    val back_female: String,
//    val back_shiny: String,
//    val back_shiny_female: String,
//    val front_default: String,
//    val front_female: String,
//    val front_shiny: String,
//    val front_shiny_female: String,
//)
//
//@Serializable
//data class Animated(
//    val back_default: String,
//    val back_female: String,
//    val back_shiny: String,
//    val back_shiny_female: String,
//    val front_default: String,
//    val front_female: String,
//    val front_shiny: String,
//    val front_shiny_female: String,
//)
//
//@Serializable
//data class OmegarubyAlphasapphire(
//    val front_default: String,
//    val front_female: String,
//    val front_shiny: String,
//    val front_shiny_female: String,
//)
//
//@Serializable
//data class XY(
//    val front_default: String,
//    val front_female: String,
//    val front_shiny: String,
//    val front_shiny_female: String,
//)
//
//@Serializable
//data class Icons(
//    val front_default: String,
//    val front_female: String,
//)
//
//@Serializable
//data class UltraSunUltraMoon(
//    val front_default: String,
//    val front_female: String,
//    val front_shiny: String,
//    val front_shiny_female: String,
//)
//
//@Serializable
//data class StatX(
//    val name: String,
//    val url: String,
//)
//
//@Serializable
//data class TypeX(
//    val name: String,
//    val url: String,
//)