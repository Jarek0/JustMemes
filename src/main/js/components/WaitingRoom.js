import React from 'react';
import Mem from './Mem';
import Error from './Error';
import DownPanel from './DownPanel';
import {Grid,Row,Col} from 'react-bootstrap';


export default class WaitingRoom extends React.Component{
    constructor(props){
        super(props);
        this.state = {memes: [],numberOfPage: 0,errorStatus: "",errorMessage: ""};
    }

    componentDidMount(){
        this.showInitialPage(0);
    }

    showMemesFromPage(page){
        $.ajax({
            url: '/mem/waiting/'+page,
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
        console.log("Welcome on waiting page!");
        $.ajax({
            url: '/mem/waiting/initialPage',
            type: 'GET',
            success: (function(data) {
                this.setState({numberOfPage:page});
                this.setState({ memes: data.content });
            }).bind(this),
            error: (function (xhr, ajaxOptions, thrownError) {
                this.setState({errorStatus: xhr.status});
                this.setState({errorMessage: xhr.responseText});
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
                    <DownPanel page="waiting_room" numberOfPage={this.state.numberOfPage}/>
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
