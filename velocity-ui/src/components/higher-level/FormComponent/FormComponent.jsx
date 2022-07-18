import React, { useState } from 'react';
import './FormComponent.css';
import 'bootstrap/dist/css/bootstrap.min.css';

function FormComponent() {
  const [title, setTitle] = useState('');
  const [desc, setDesc] = useState('');
  const [author, setAuthor] = useState('');

  const [tutorial, setTutorial] = useState({
    title: '',
    desc: '',
    author: ''
  });

  const handlePreviewRequest = () => {
    console.log(title, desc, author);

    const newTutorial = {...tutorial};

    newTutorial.title = title;
    newTutorial.desc = desc;
    newTutorial.author = author;

    setTutorial(newTutorial);
    
    console.log(tutorial)

  }

  return(
    <div className="FormComponent">
      <input value={title} 
            onChange={e => setTitle(e.target.value)} 
            className='form-control' 
            type={'text'} 
            placeholder='Title' 
      />
      <input value={desc} 
            onChange={e => setDesc(e.target.value)}
            className='form-control' 
            type={'text'} 
            placeholder='Description' />
      <input value={author} 
            onChange={e => setAuthor(e.target.value)} 
            className='form-control' 
            type={'text'} 
            placeholder='Author' />
      
      <button className='btn btn-primary' onClick={handlePreviewRequest}>Preview</button>
    </div>
  );  
}

export default FormComponent;

