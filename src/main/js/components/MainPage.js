import React from 'react';
import Mem from './Mem';
import Error from './Error';
import DownPanel from './DownPanel';
import {Grid,Row,Col} from 'react-bootstrap';


export default class MainPage extends React.Component{
    constructor(props){
        super(props);
        this.state = {memes: [],numberOfPage: 0,errorStatus: "",errorMessage: ""};


    }

    componentDidMount(){

        this.showInitialPage(0);
    }

    showMemesFromPage(page){
        $.ajax({
            url: '/mem/page/'+page,
            type: 'GET',
            success: (function(data) {
                this.setState({numberOfPage:page});
                this.setState({ memes: data.content });
            }).bind(this),
            error: (function (xhr, ajaxOptions, thrownError) {
                this.setState({errorStatus: xhr.status});
                this.setState({errorMessage: xhr.responseText});
            }).bind(this)
        })
        

        window.scrollTo(0,0);
    }
    showInitialPage(page){
        $.ajax({
            url: '/mem/initialPage',
            type: 'GET',
            success: (function(data) {
                this.setState({numberOfPage:page});
                this.setState({ memes: data.content });
            }).bind(this),
            error: (function (xhr, ajaxOptions, thrownError) {
                this.setState({errorStatus: xhr.status});
                this.setState({errorMessage:  (JSON.parse(xhr.responseText)).message});
            }).bind(this)
        });
    }

    componentWillReceiveProps(nextProps) {
        if (nextProps.params.page !== this.props.params.page || nextProps.params.page !== undefined) {
            this.props.params.page=nextProps.params.page;
            this.showMemesFromPage(this.props.params.page);
        }
    }
    render(){
        if(this.state.errorMessage.length===0 && this.state.errorStatus.length===0){
            return(
                <div>
                    {

                        this.state.memes.map((mem) =>
                            <Mem title={mem.title} key={'mem-'+mem.id} id={mem.id} fileType={mem.fileType} />
                        )

                    }
                    <DownPanel numberOfPage={this.state.numberOfPage}/>
                </div>);
        }
        else{
            return(
                <div>
                        <Error status={this.state.errorStatus} mess={this.state.errorMessage}/>
                </div>);
        }


    }
}
