import React, { useState } from 'react';
import FormComponent from '../../components/higher-level/FormComponent/FormComponent';
import PreviewComponent from '../../components/higher-level/PreviewComponent/PreviewComponent';
import HeaderComponent from '../../layouts/Header/HeaderComponent';

function HomePage() {
  const [source, setSource] = useState('http://localhost:8088/templates/template1/index/' + Date.now());

  const handleRequest = ( tutorial) => {
    const requestOptions = {
      method: 'POST',
	    credentials : 'include', // to send HTTP only cookies
      headers: {'Content-Type':'application/json'},
      body: JSON.stringify(tutorial)
    }

    const request = fetch('http://localhost:8088/templates/configuration/', requestOptions);

    request.then(response => {
      setSource('http://localhost:8088/templates/template1/index/' + Date.now());
      console.log(response)
    });
  }

  const handleExport = () => {
    const requestOptions = {
      method: 'POST',
	    credentials : 'include', // to send HTTP only cookies
      headers: {'Content-Type':'application/json'},
    }

    const request = fetch('http://localhost:8088/templates/configuration/export', requestOptions);
    request.then(response => {
      console.log(response)
    });

  }

  return (
    <div className='App'>
      <HeaderComponent />
      <div className="row">
        <div className='col-sm-6'>
          <FormComponent 
            handleRequestFunction={handleRequest}
            handleExportRequestFunction={handleExport}
          />
        </div>
        <div className='col-sm-6'>
          <PreviewComponent source={source}/>
        </div>
      </div>
    </div>
  );
}

export default HomePage;