package jp.hiroshi.nov.m.a7minuteworkout

/** Model representing a single exercise item. */
class ExerciseModel(
    /** Unique identifier for the exercise. */
    private var id: Int,
    /** Display name of the exercise. */
    private var name: String,
    /** Image resource for the exercise. */
    private var image: Int,
    /** Flag indicating the exercise is finished. */
    private var isCompleted: Boolean,
    /** Flag indicating the exercise is currently selected. */
    private var isSelected: Boolean
){
    /** Returns the id of the exercise. */
    fun getId(): Int {
        return id
    }

    /** Updates the id of the exercise. */
    fun setId(id: Int) {
        this.id = id
    }

    /** Returns the name of the exercise. */
    fun getName(): String {
        return name
    }

    /** Sets the name of the exercise. */
    fun setName(name: String) {
        this.name = name
    }

    /** Returns the image resource id. */
    fun getImage(): Int {
        return image
    }

    /** Sets the image resource id. */
    fun setImage(image: Int) {
        this.image = image
    }

    /** True if the exercise has been completed. */
    fun getIsCompleted(): Boolean {
        return isCompleted
    }

    /** Marks the exercise as completed or not. */
    fun setIsCompleted(isCompleted: Boolean) {
        this.isCompleted = isCompleted
    }

    /** True if the exercise is currently selected. */
    fun getIsSelected(): Boolean {
        return isSelected
    }

    /** Marks the exercise as selected or not. */
    fun setIsSelected(isSelected: Boolean) {
        this.isSelected = isSelected
    }

}
