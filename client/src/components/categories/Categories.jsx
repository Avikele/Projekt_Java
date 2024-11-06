import "./categories.scss"
import { useState } from "react";
const Categories = ({isOpen, onClose, onSelectCategory}) => {
    const [isAddingNewCategory, setIsAddingNewCategory] = useState(false);
    const [newCategory, setNewCategory] = useState("");

    const [categories, setCategories] = useState([
        "Fantasy",
        "Sci-Fi",
        "Thriller",
        "Horror",
        "Romance",
        "Drama",
        "Comedy",
        "Biography",
        "History",
        "Poetry"
    ]);
    if (!isOpen) return null; 

    

    

    const handleCategoryClick = (category) => {
        onSelectCategory(category); 
        onClose(); 
    };

    const handleAddCategoryClick = () => {
        setIsAddingNewCategory(true);
    };

    const handleNewCategoryChange = (event) => {
        setNewCategory(event.target.value);
    };

    const handleNewCategorySubmit = (event) => {
        event.preventDefault();
        if (newCategory.trim()) {
            
            setCategories([...categories, newCategory.trim()]); 
            setNewCategory(""); 
        }
        setIsAddingNewCategory(false);
    };

    return(
        <div className="categories-card">
            <div className="categories">
                <input type='text' placeholder='Search'/>
                <div className="category-list">
                    {categories.map((category, index) => (
                        <div key={index} className="category-item"  onClick={() => handleCategoryClick(category)}>
                            {category}
                        </div>
                    ))}


                    {isAddingNewCategory ? (
                        <form onSubmit={handleNewCategorySubmit}>
                            <input
                                type="text"
                                value={newCategory}
                                onChange={handleNewCategoryChange}
                                placeholder="New category name"
                                required
                            />
                            <button type="submit">Add</button>
                        </form>
                    ) : (
                        <button className="addCategory" onClick={handleAddCategoryClick}>
                            Add new category
                        </button>
                    )}
                    
                </div>
                <button onClick={onClose}>Close</button>
            </div>
        </div>
    )
}

export default Categories