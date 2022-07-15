import React from 'react';
import ReactDOM from 'react-dom';
import PreviewComponent from './PreviewComponent';

it('Should mount', () => {
  const div = document.createElement('div');
  ReactDOM.render(<PreviewComponent />, div);
  ReactDOM.unmountComponentAtNode(div);
});