import { useState, useRef } from "react";
import "./addBook.scss"
import Categories from "../../components/categories/Categories";

const AddBook = () => {
  const [categories, setCategories] = useState([]);
  const [selectedImage, setSelectedImage] = useState(null);
  const fileInputRef = useRef(null);
  const [isModalOpen, setModalOpen] = useState(false);

  const openModal = () => setModalOpen(true);
  const closeModal = () => setModalOpen(false);

  const handleAddCategory = (newCategory) => {
    setCategories((prevCategories) => [...prevCategories, newCategory]);
  };


  const handleRemoveCategory = (category) => {
    setCategories(categories.filter((cat) => cat !== category));
  };

  const handleImageChange = (event) => {
    const file = event.target.files[0];
    if (file) {
      const reader = new FileReader();
      reader.onload = () => {
        setSelectedImage(reader.result);
      };
      reader.readAsDataURL(file);
    }
  };


  const handleButtonClick = () => {
    fileInputRef.current.click();
  };

  return (
    <div className="container">
      <div className="left-panel">
        <div className="image-placeholder">
          {selectedImage ? (
            <img src={selectedImage} alt="Selected" className="image" />
          ) : (
            <span className="plus">+</span>
          )}
        </div>
        <input
          type="file"
          accept="image/*"
          onChange={handleImageChange}
          className="file-input"
          ref={fileInputRef}
          style={{ display: "none" }} 
        />

    
        <button className="select-image-button" onClick={handleButtonClick}>
          Select an image
        </button>
      </div>

      <div className="right-panel">
        <input type="text" placeholder="Title" className="input" />
        <input type="text" placeholder="Author" className="input" />
        <input type="text" placeholder="Publisher" className="input" />
        <input type="text" placeholder="Pages" className="input" />
        <input type="text" placeholder="Title" className="input" />

        <button className="add-category-button" onClick={openModal}>
          Add Category
        </button>

        <div className="categories-container">
          {categories.map((category, index) => (
            <div className="category-tag" key={index}>
              {category}
              <span
                className="remove-category"
                onClick={() => handleRemoveCategory(category)}
              >
                x
              </span>
            </div>
          ))}
        </div>

        <button className="add-book-button">Add book</button>
      </div>
      <Categories isOpen={isModalOpen} onClose={closeModal} onSelectCategory={handleAddCategory}/>
    </div>
  );
};

export default AddBook;
