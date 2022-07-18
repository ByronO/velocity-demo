import FormComponent from '../../components/higher-level/FormComponent/FormComponent';
import PreviewComponent from '../../components/higher-level/PreviewComponent/PreviewComponent';
import HeaderComponent from '../../layouts/Header/HeaderComponent';

function HomePage() {

  return (
    <div className='App'>
      <HeaderComponent />
      <div className="row">
        <div className='col-sm-6'>
          <FormComponent title="Test props"/>
        </div>
        <div className='col-sm-6'>
          <PreviewComponent source="http://www.test.cvex.com/" />
        </div>
      </div>
    </div>
  );
}

export default HomePage;