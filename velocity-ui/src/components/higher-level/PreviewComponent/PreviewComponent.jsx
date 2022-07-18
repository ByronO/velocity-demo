import React from 'react';
import './PreviewComponent.css';

function PreviewComponent(props) {
    return(
        <iframe className='container' src={props.source} height={700} width={700} />
    );    
}

export default PreviewComponent;