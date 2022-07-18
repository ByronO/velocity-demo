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
          <PreviewComponent source="http://localhost:8088/templates/template1/index" />
        </div>
      </div>
    </div>
  );
}

export default HomePage;