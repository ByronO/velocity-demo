import React, { useState } from 'react';
import './FormComponent.css';
import 'bootstrap/dist/css/bootstrap.min.css';

function FormComponent( props ) {
  const [brandName, setBrandName] = useState('');
  const [desc, setDesc] = useState('');
  const [author, setAuthor] = useState('');

  const [tutorial, setTutorial] = useState({
    brandName: '',
    desc: '',
    author: ''
  });

  const handlePreviewRequest = () => {

    const newTutorial = {...tutorial};

    newTutorial.brandName = brandName;
    newTutorial.desc = desc;
    newTutorial.author = author;

    setTutorial(newTutorial);

    props.handleRequestFunction(newTutorial);

  }

  const handleExportRequest = () => {
    props.handleExportRequestFunction();
  }

  return(
    <div className="FormComponent">
      <input value={brandName} 
            onChange={e => setBrandName(e.target.value)} 
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
      <button className='btn btn-primary' onClick={handleExportRequest}>Export</button>
    </div>
  );  
}

export default FormComponent;

